/**
 * @Author HuyVu
 * @CreatedDate 5/5/2023 2:07 PM
 */
package io.huyvu.reboot.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ResourceHandleRegistry implements WebMvcConfigurer {
    @Value("${reboot.upload.dir}")
    private String UPLOAD_DIR;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/v1/internal/resources/board/**")
                .addResourceLocations("file:" + UPLOAD_DIR + "/");
    }
}
