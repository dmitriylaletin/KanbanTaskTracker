package com.eisgroup.tasktracker.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:51
 */
public class ServletUtils {
        public static String getCookieValue(HttpServletRequest request, String name) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return cookie.getValue();
                    }
                }
            }
            return null;
        }

        public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
            Cookie cookie = new Cookie(name, value);
            cookie.setPath("/");
            cookie.setMaxAge(maxAge);
            response.addCookie(cookie);
        }

        public static void removeCookie(HttpServletResponse response, String name) {
            addCookie(response, name, null, 0);
        }
}
