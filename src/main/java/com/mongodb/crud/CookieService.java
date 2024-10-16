package com.mongodb.crud;

public class CookieService {
    static String cookie;

    public static String getCookie() {
        return cookie;
    }

    public static void setCookie(String cookie) {
        CookieService.cookie = cookie;
    }
}
