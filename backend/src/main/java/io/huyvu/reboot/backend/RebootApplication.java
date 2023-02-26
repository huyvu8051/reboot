package io.huyvu.reboot.backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RebootApplication {
    public static void main(String[] args) {
        //SpringApplication.run(RebootApplication.class, args);
        ConfigurableApplicationContext run = new SpringApplicationBuilder(RebootApplication.class)
                .beanNameGenerator(new FullyQualifiedAnnotationBeanNameGenerator())
                .run(args);
    }
}
