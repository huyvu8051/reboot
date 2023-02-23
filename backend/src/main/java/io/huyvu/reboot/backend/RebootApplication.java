package io.huyvu.reboot.backend;

import io.huyvu.reboot.backend.config.CustomGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class RebootApplication {
    public static void main(String[] args) {
        //SpringApplication.run(RebootApplication.class, args);
        new SpringApplicationBuilder(RebootApplication.class)
                .beanNameGenerator(new CustomGenerator())
                .run(args);
    }
}
