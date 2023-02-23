package io.huyvu.reboot.backend.config.mybatis;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.context.annotation.Configuration;


@org.aspectj.lang.annotation.Aspect
@Configuration
@RequiredArgsConstructor
public class Aspect {

    private final Repository ucRepo;

    @Around("@annotation(org.apache.ibatis.annotations.Insert) && execution(long *(..))")
    public Object myMethodProxy(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();

        long lastInsertId = ucRepo.getLastInsertId();
        return lastInsertId;
    }
}