package com.eisgroup.tasktracker.beans;

import com.eisgroup.tasktracker.utils.Constants;
import com.eisgroup.tasktracker.utils.FacesUtils;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 02:04
 */
@ManagedBean
@SessionScoped
public class LocaleChanger implements Serializable {
    private static final long serialVersionUID = 1L;

    private Locale locale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();

    public LocaleChanger() {
        checkCookieLocale();
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public String setLanguage(String language) {
        this.locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        FacesUtils.setCookie(Constants.LOCALE, language);
        return "root";
    }

    private void checkCookieLocale() {
        final String cookie = FacesUtils.getCookie(Constants.LOCALE);
        if (cookie != null) {
            final Iterator<Locale> supportedLocales =
                    FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
            while (supportedLocales.hasNext()) {
                if (supportedLocales.next().getLanguage().equals(cookie)) {
                    locale = new Locale(cookie);
                }
            }
        }
    }
}
