/**
 * @Author HuyVu
 * @CreatedDate 2/22/2023 3:27 PM
 */
package io.huyvu.reboot.backend.config.mybatis;

import io.huyvu.reboot.backend.util.SecurityUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.HashMap;
import java.util.Map;

@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class MybatisAuditingInterceptor implements Interceptor {
    public static final String SET_USER_CTX_QUERY = "SET @USER_CTX = #{USER_CTX};";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object parameter = invocation.getArgs()[1];

        if(parameter instanceof Map mapParams){
            mapParams.put("USER_CTX", SecurityUtils.currentContext().username());
        }else {
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("param1", parameter);
            mapParams.put("USER_CTX", SecurityUtils.currentContext().username());
        }
        return invocation.proceed();
    }
}
