package io.huyvu.reboot.backend;

import io.huyvu.reboot.backend.util.FulltextSearchUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

import java.util.Arrays;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class}, scanBasePackages = {"io.huyvu.mybatix", "io.huyvu.reboot.backend"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RebootApplication {


    public static void main(String[] args) {

        ConfigurableApplicationContext run = new SpringApplicationBuilder(RebootApplication.class)
                .beanNameGenerator(new FullyQualifiedAnnotationBeanNameGenerator())
                .run(args);

    }


}
