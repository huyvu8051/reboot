package io.huyvu.reboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RebootApplication {


    public static void main(String[] args) {

        new SpringApplicationBuilder(RebootApplication.class)
                .beanNameGenerator(new FullyQualifiedAnnotationBeanNameGenerator())
                .run(args);

        var mood = "sad";

    }

    @GetMapping("/getUsers")
    public String getUsers(String name) {
        String query = "SELECT * FROM users WHERE name = '" + name + "'";
        // Execute the query and return results (DO NOT actually execute this in real code!)
        return query;
    }


}
