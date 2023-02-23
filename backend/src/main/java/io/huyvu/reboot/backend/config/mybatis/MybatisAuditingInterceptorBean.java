package io.huyvu.reboot.backend.config.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisAuditingInterceptorBean {
    
    @Bean
    public MybatisAuditingInterceptor baseEntityInterceptor() {
        return new MybatisAuditingInterceptor();
    }

}


