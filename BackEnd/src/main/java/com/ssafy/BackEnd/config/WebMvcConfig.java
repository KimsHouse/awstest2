package com.ssafy.BackEnd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

public class WebMvcConfig implements WebMvcConfigurer {

    @Value("C:/Users/SSAFY/Desktop/S06P12C201/BackEnd/profileImage/")
    private String profileUploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/profile_imgs/**")
                .addResourceLocations("file:///"+profileUploadFolder)
                .setCachePeriod(60*10*6)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
