package com.yizhou.yiblog.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    //one year:
    public static final int DEFAULT_AGE = 60 * 60 * 24 * 365;

    public static final String DOMAIN = "localhost";

    /**
     * set up cookie value
     *
     * @param response
     * @param key
     * @param value
     */

    public static void setupCookie(HttpServletResponse response, String key, String value) {

        setupCookie(response, key, value, DEFAULT_AGE);

    }

    public static void setupCookie(HttpServletResponse response, String key, String value, int age) {

        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setDomain(DOMAIN);
        cookie.setMaxAge(age);

        response.addCookie(cookie);

    }

    /**
     * get Cookie
     *
     * @param request
     * @param key
     * @return
     */
    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * delete cookie
     */
    public static void deleteCookie(HttpServletResponse response, String key) {
        //make cookie's age 0
        setupCookie(response, key, null, 0);
    }
}
