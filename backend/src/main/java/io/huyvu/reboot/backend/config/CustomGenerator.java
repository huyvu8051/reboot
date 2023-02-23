package io.huyvu.reboot.backend.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

import java.util.function.UnaryOperator;

public class CustomGenerator extends AnnotationBeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {

        UnaryOperator<String> fun= pkgName->{
             int lastIndex = pkgName.lastIndexOf ('.');
            if (lastIndex!=-1){  
                pkgName=pkgName.substring (0, lastIndex);
            }
            return pkgName;
        };
        String className = super.generateBeanName(definition, registry);
        String packagename = definition.getBeanClassName();
        return (fun.apply(packagename) + "." + className);
    }
}