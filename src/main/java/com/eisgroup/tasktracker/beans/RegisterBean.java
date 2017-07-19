package com.eisgroup.tasktracker.beans;

import com.eisgroup.tasktracker.service.UserService;
import com.eisgroup.tasktracker.utils.AppUtils;
import com.eisgroup.tasktracker.utils.FacesUtils;
import org.hibernate.HibernateException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 02:09
 */
@ManagedBean
@ViewScoped
public class RegisterBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{userService}")
    private transient UserService userService;

    private String login;
    private String password;
    private String email;

    public RegisterBean() {
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String register() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            String md5Password = AppUtils.md5(password);
            userService.addNewUser(login, md5Password, email);

            FacesMessage message = FacesUtils.createFacesMessage("registration_successful", FacesMessage.SEVERITY_INFO);
            facesContext.addMessage("login_form", message);
        } catch (HibernateException e) {
            FacesMessage message =
                    FacesUtils.createFacesMessage("registration_error", FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage("login_form", message);
        }
        return "index";
    }
}
