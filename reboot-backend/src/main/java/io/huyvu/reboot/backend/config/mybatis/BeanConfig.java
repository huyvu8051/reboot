package io.huyvu.reboot.backend.config.mybatis;

import io.huyvu.reboot.RebootApplication;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(BeanNameGenerator bng) {
        var config = new MapperScannerConfigurer();
        config.setBasePackage(RebootApplication.class.getPackageName());
        config.setNameGenerator(bng);
        config.setAnnotationClass(Mapper.class);
        return config;
    }
}
