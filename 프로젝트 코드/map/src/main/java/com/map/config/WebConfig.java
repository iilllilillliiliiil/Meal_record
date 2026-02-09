package com.map.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /img/uploadImg/** URL을 실제 파일 시스템 경로와 매핑
        registry.addResourceHandler("/img/uploadImg/**")
                .addResourceLocations("file:src/main/resources/static/img/uploadImg/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 또는 특정 도메인
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}