package io.huyvu.reboot.backend.config.mybatis;

import io.huyvu.reboot.backend.biz.user.dashboard.v1.Paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.huyvu.reboot.backend.config.mybatis.MyBatisSelectProcessor.*;
import static io.huyvu.reboot.backend.util.SecurityUtils.username;


@Slf4j
@Configuration
@Aspect
@RequiredArgsConstructor
public class AspectConfig {
    private final AuditingRepository ucRepo;

    private final SqlSessionTemplate sqlSession;

    @Around("@annotation(org.apache.ibatis.annotations.Insert) && execution(long *(..))")
    public Object returnLastGeneratedId(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        long lastInsertId = ucRepo.getLastInsertId();
        return lastInsertId;
    }

    @Around("@annotation(org.apache.ibatis.annotations.Select) && execution(Page *(..))")
    public Object returnPage(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String itemsMethodName = signature.getMethod().getDeclaringClass().getName() + PAGEABLE + "." + ITEMS_METHOD_NAME_PREFIX + methodName;
        String totalCountMethodName = signature.getMethod().getDeclaringClass().getName() + PAGEABLE + "." + TOTAL_COUNT_METHOD_NAME_PREFIX + methodName;

        var args = joinPoint.getArgs();

        var parameterNames = signature.getParameterNames();

        Map<String, Object> argsMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            var arg = args[i];
            if (arg instanceof Paging paging) {
                argsMap.put("limit", paging.getLimit());
                argsMap.put("offset", paging.getOffset());
            } else {
                String parameterName = parameterNames[i];
                argsMap.put(parameterName, arg);
            }

        }
        List result = sqlSession.selectList(itemsMethodName, argsMap);

        var totalCount = sqlSession.selectOne(totalCountMethodName, argsMap);
        return new Page() {
            @Override
            public int getTotalCount() {
                return Integer.parseInt(totalCount.toString());
            }

            @Override
            public List getItems() {
                return result;
            }
        };
    }

    @Before("@annotation(org.apache.ibatis.annotations.Insert) || @annotation(org.apache.ibatis.annotations.Update)")
    public void setUserCtx() {
        ucRepo.setUserCtx(username());
    }
}