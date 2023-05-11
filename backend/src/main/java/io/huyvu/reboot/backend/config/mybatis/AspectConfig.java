package io.huyvu.reboot.backend.config.mybatis;

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

import static io.huyvu.reboot.backend.config.mybatis.MyBatisSelectProcessor.PAGEABLE;
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
        String fullMethodName = signature.getMethod().getDeclaringClass().getName() + PAGEABLE + "." + methodName;

        var args = joinPoint.getArgs();

        var parameterNames = signature.getParameterNames();
        var parameterTypes = signature.getParameterTypes();


        Map<String, Object> argsMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            String parameterName = parameterNames[i];
            argsMap.put(parameterName, args[i]);
        }
        List result = sqlSession.selectList(fullMethodName, argsMap);

        var rowsFound = ucRepo.selectRowsFound();
        return new Page() {
            @Override
            public int getTotalCount() {
                return rowsFound;
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