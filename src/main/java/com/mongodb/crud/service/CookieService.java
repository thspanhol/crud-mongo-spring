package com.mongodb.crud.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CookieService {

    private String cookie;


    public void createCookie(HttpServletRequest request, HttpServletResponse response) {

        //Pattern pattern = Pattern.compile("/");
        //Matcher matcher = pattern.matcher(request.getRequestURI());

        //String index = request.getRequestURI().substring(13);
        String[] index = request.getRequestURI().split("/");
        String lastIndex = index[index.length - 1];
        Cookie cookie = new Cookie("lastUser", lastIndex);
        cookie.setMaxAge(60 * 60);
        this.cookie = cookie.getValue();
        response.addCookie(cookie);
    }

    public String getCookie() {
        return this.cookie;
    }
}
