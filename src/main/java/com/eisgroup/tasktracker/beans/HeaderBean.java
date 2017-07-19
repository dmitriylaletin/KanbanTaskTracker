package com.eisgroup.tasktracker.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 02:01
 */
@ManagedBean
@ViewScoped
public class HeaderBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{authManager.currentUser.login}")
    private String userLogin;
    private boolean loginRendered = true;

    public HeaderBean() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public boolean isLoginRendered() {
        return loginRendered;
    }

    public void setLoginRendered(boolean loginRendered) {
        this.loginRendered = loginRendered;
    }

    public void switchRenderedState() {
        loginRendered = !loginRendered;
    }
}
