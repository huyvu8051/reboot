package io.huyvu.reboot.backend.config;

import io.huyvu.reboot.backend.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
public class MyBatisConfig {
    @Bean
    public BaseEntityInterceptor baseEntityInterceptor() {
        return new BaseEntityInterceptor();
    }
}


@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
class BaseEntityInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String sql = boundSql.getSql();
        // Modify the SQL statement
        sql = "SET @USER_CTX = ?;" + sql;
        Map<String, Object> mapParams = (Map<String, Object>) parameter;
        mapParams.put("USER_CTX", SecurityUtils.currentContext().username());
        // Update the BoundSql object

        ParameterMapping pm = new ParameterMapping.Builder(mappedStatement.getConfiguration(), "USER_CTX", Object.class).build();

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        List<ParameterMapping> pm2 = new ArrayList<>();
        pm2.add(pm);
        pm2.addAll(parameterMappings);

        MappedStatement newMappedStatement = copyFromMappedStatement(mappedStatement, new StaticSqlSource(mappedStatement.getConfiguration(), sql, pm2));
        invocation.getArgs()[0] = newMappedStatement;
        List<ParameterMapping> parameterMappings1 = newMappedStatement.getParameterMap().getParameterMappings();
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // Set plugin properties, if any
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.keyProperty(StringUtils.join(ms.getKeyProperties(), ","));
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }
}



