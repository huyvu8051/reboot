package io.huyvu.mybatix;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.huyvu.mybatix.MyBatisSelectProcessor.*;

@Slf4j
@Configuration
@Aspect
@RequiredArgsConstructor
public class AspectConfig {

    private final SqlSessionTemplate sqlSession;


    @Around("@annotation(org.apache.ibatis.annotations.Select) && execution(io.huyvu.mybatix.Page *(..))")
    public Object returnPage(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String itemsMethodName = signature.getMethod().getDeclaringClass().getName() + PAGEABLE_CLASS_PREFIX + "." + ITEMS_METHOD_NAME_PREFIX + methodName;
        String totalCountMethodName = signature.getMethod().getDeclaringClass().getName() + PAGEABLE_CLASS_PREFIX + "." + TOTAL_COUNT_METHOD_NAME_PREFIX + methodName;

        var args = joinPoint.getArgs();
        var parameterNames = signature.getParameterNames();
        Map<String, Object> argsMap = getArgsMap(args, parameterNames);

        List items = sqlSession.selectList(itemsMethodName, argsMap);
        var totalCount = sqlSession.selectOne(totalCountMethodName, argsMap);

        return new Page() {
            @Override
            public int getTotalCount() {
                return Integer.parseInt(totalCount.toString());
            }

            @Override
            public List getItems() {
                return items;
            }

            @Override
            public String toString() {
                return "(totalCount: %d, items: %s )".formatted(totalCount, items);
            }
        };
    }

    private static Map<String, Object> getArgsMap(Object[] args, String[] parameterNames) {
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
        return argsMap;
    }

}