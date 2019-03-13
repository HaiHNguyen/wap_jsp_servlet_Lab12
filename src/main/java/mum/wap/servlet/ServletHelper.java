package mum.wap.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ServletHelper {

    private String cookieName = "user";

    public Cookie getCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {

                    System.out.println(req.getRemoteAddr() + "::Cookie::{" + cookie.getName()
                                                        + "," + cookie.getValue() + "}");
                    return cookie;
                }
            }
        }
        return null;
    }
    public Cookie createCookie( String value, int maxAge) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(maxAge);
        return cookie;
    }
    public void deleteCookie( HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookie.setMaxAge(0);
                }
            }
        }

    }

}

