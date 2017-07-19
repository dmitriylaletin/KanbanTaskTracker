package com.eisgroup.tasktracker.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:50
 */
public class FacesUtils {

    public static FacesMessage createFacesMessage(String bundleKey, FacesMessage.Severity severity) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle =
                ResourceBundle.getBundle(Constants.MESSAGES_RESOURCE,
                        facesContext.getViewRoot().getLocale());
        FacesMessage message = new FacesMessage(bundle.getString(bundleKey));
        message.setSeverity(severity);
        return message;
    }

    public static HttpServletRequest getRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpServletRequest)
                facesContext.getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpServletResponse)
                facesContext.getExternalContext().getResponse();
    }

    public static void setCookie(String name, String value) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("maxAge", (int) TimeUnit.DAYS.toSeconds(365));
        properties.put("path", "/");
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .addResponseCookie(name, value, properties);
    }

    public static String getCookie(String name) {
        Cookie cookie = (Cookie) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestCookieMap().get(name);
        return cookie != null ? cookie.getValue() : null;
    }

    public static void destroySession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}
