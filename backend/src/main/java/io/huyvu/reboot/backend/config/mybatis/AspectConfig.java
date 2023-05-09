package io.huyvu.reboot.backend.config.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
/*

    @Around("@annotation(org.apache.ibatis.annotations.Select) && execution(Page *(..))")
    public Object returnPage(ProceedingJoinPoint joinPoint) throws Throwable {
        List result = null;

        joinPoint.proceed();


        MappedStatement mappedStatement = sqlSession.getConfiguration().getMappedStatement("com.example.MyMapper.selectById");
        var resultMaps = mappedStatement.getResultMaps();
        */
/*sqlSession.select("io.huyvu.reboot.backend.biz.user.dashboard.v1.Repository.selectAttachments", joinPoint.getArgs(), ctx->{
            var resultCount = ctx.getResultCount();
            var resultObject = ctx.getResultObject();
        });*//*


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
*/

    @Before("@annotation(org.apache.ibatis.annotations.Insert) || @annotation(org.apache.ibatis.annotations.Update)")
    public void setUserCtx() {
        ucRepo.setUserCtx(username());
    }
}