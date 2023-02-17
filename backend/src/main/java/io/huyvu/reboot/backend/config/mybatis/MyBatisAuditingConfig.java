package io.huyvu.reboot.backend.config.mybatis;

import io.huyvu.reboot.backend.util.SecurityUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class MyBatisAuditingConfig {
    @Bean
    public BaseEntityInterceptor baseEntityInterceptor() {
        return new BaseEntityInterceptor();
    }
}


@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
class BaseEntityInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object parameter = invocation.getArgs()[1];
        Map<String, Object> mapParams = (Map<String, Object>) parameter;
        mapParams.put("USER_CTX", SecurityUtils.currentContext().username());
        return invocation.proceed();
    }
}