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
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class AuditingInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Executor executor = (Executor) invocation.getTarget();
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Configuration configuration = mappedStatement.getConfiguration();



        executor.query(configuration.getMappedStatement(Repository.class.getCanonicalName() + ".setUserCtx"),
                SecurityUtils.currentContext().username(),
                RowBounds.DEFAULT,
                Executor.NO_RESULT_HANDLER);


        return invocation.proceed();
    }


}




