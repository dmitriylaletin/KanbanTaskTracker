package com.eisgroup.tasktracker.beans;

import com.eisgroup.tasktracker.exceptions.LoginException;
import com.eisgroup.tasktracker.service.AuthManager;
import com.eisgroup.tasktracker.utils.FacesUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 02:06
 */
@ManagedBean
@RequestScoped
public class LoginBean  {

    @ManagedProperty(value = "#{authManager}")
    private AuthManager authManager;

    private String login;
    private String password;
    private boolean remember;

    public LoginBean() {
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String login() {
        try {
            authManager.login(login, password, remember);
            return "main";
        } catch (LoginException e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage message = FacesUtils.createFacesMessage("auth_error", FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage("login_form", message);
            return "index";
        }
    }

    public String logout() {
        authManager.logout();
        return "/index.xhtml?faces-redirect=true";
    }
}
