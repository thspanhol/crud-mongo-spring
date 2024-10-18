package com.mongodb.crud.service;

import com.mongodb.crud.model.CookieClass;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieService {

    public static void createCookie(HttpServletRequest request, HttpServletResponse response) {
        String index = request.getRequestURI().substring(13);
        Cookie cookie = new Cookie("lastUser", index);
        cookie.setMaxAge(60 * 60);
        CookieClass.setCookie(index);
        response.addCookie(cookie);
    }
}
