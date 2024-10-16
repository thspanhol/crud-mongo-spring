package com.mongodb.crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
                .excludePathPatterns("/v1/usuarios/nome=**")
                .addPathPatterns("/v1/usuarios/**");
                //.addPathPatterns("/v1/usuarios/**", "/v1/usuarios/last");
                //.addPathPatterns("/**");
    }
}
