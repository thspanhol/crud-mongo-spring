package com.mongodb.crud.config;

import com.mongodb.crud.model.CookieClass;
import com.mongodb.crud.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

//TODO: Essa classe não é o LoggingInterceptor e sim o CookieInterceptor
// Feito
//TODO: Implementa a CookieService e injeta ela aqui pra fazer a manipulação de cookies
// Feito
@Component
public class CookieInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("URI " + request.getRequestURI());

        if (Objects.equals(request.getMethod(), "GET") && !request.getRequestURI().equals("/v1/users")) {
            CookieService.createCookie(request, response);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle - Status da resposta: " + response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("After Completion - Requisição finalizada");
    }
}
