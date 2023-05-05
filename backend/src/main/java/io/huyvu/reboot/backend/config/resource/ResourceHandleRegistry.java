/**
 * @Author HuyVu
 * @CreatedDate 5/5/2023 2:07 PM
 */
package io.huyvu.reboot.backend.config.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ResourceHandleRegistry implements WebMvcConfigurer {
    private final String UPLOAD_DIR = "file:D:/reboot/resources/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/internal/resources/board/**")
                .addResourceLocations(UPLOAD_DIR);
    }
}
