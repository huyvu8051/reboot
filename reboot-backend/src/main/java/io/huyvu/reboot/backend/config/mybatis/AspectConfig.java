package io.huyvu.reboot.backend.config.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import static io.huyvu.reboot.security.util.SecurityUtils.username;


@Slf4j
@Configuration
@Aspect
@RequiredArgsConstructor
public class AspectConfig {
    private final AuditingRepository ucRepo;

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