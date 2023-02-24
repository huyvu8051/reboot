package io.huyvu.reboot.backend.config.mybatis;

import io.huyvu.reboot.backend.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;


@Configuration
@org.aspectj.lang.annotation.Aspect
@RequiredArgsConstructor
public class Aspect {

    private final AuditingRepository ucRepo;

    @Around("@annotation(org.apache.ibatis.annotations.Insert) && execution(long *(..))")
    public Object returnLastGeneratedId(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();

        long lastInsertId = ucRepo.getLastInsertId();
        return lastInsertId;
    }
    @Before("@annotation(org.apache.ibatis.annotations.Insert) || @annotation(org.apache.ibatis.annotations.Update)")
    public void setUserCtx(JoinPoint joinPoint) {
        ucRepo.setUserCtx(SecurityUtils.currentContext().username());
    }
}