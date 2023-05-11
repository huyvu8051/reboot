package io.huyvu.reboot.backend.config.mybatis;

import io.huyvu.mybatix.Page;
import io.huyvu.mybatix.Paging;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
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

import static io.huyvu.mybatix.MyBatisSelectProcessor.*;
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

    @Before("@annotation(org.apache.ibatis.annotations.Insert) || @annotation(org.apache.ibatis.annotations.Update)")
    public void setUserCtx() {
        ucRepo.setUserCtx(username());
    }
}