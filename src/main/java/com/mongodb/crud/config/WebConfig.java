package com.mongodb.crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CookieInterceptor cookieInterceptor;

    public WebConfig(CookieInterceptor cookieInterceptor) {
        this.cookieInterceptor = cookieInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cookieInterceptor)
                .excludePathPatterns("/v1/users/name=**", "/v1/users/last")
                .addPathPatterns("/v1/users/**");
    }
}
