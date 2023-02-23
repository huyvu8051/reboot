package io.huyvu.reboot.backend.config.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    
    @Bean
    public AuditingInterceptor auditingInterceptor() {
        return new AuditingInterceptor();
    }

}


