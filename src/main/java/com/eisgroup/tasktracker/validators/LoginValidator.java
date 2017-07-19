package com.eisgroup.tasktracker.validators;

import com.eisgroup.tasktracker.service.UserService;
import com.eisgroup.tasktracker.utils.FacesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:55
 */
@Component("LoginValidator")
@Scope("request")
public class LoginValidator implements Validator {
    @Autowired
    UserService userService;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        checkLoginLength(value);

        checkFirstCharIsLetter(value);

        checkSpecialCharacters(value);

        checkLoginExists(value);
    }

    private void checkLoginLength(Object value) {
        final int loginLength = value.toString().length();
        if ((loginLength < 3) || (loginLength > 15)) {
            returnMessage("login_length_error");
        }
    }

    private void checkFirstCharIsLetter(Object value) {
        if (!Character.isLetter(value.toString().charAt(0))) {
            returnMessage("login_first_letter_error");
        }
    }

    private void checkSpecialCharacters(Object value) {
        final String lettersAndDigits = "[a-zA-Z0-9]+";
        if (!value.toString().matches(lettersAndDigits)) {
            returnMessage("login_special_characters");
        }
    }

    void checkLoginExists(Object value) {
        if (userService.getUserByLogin(value.toString()) != null) {
            returnMessage("login_already_exists");
        }
    }

    void returnMessage(String bundleKey) {
        FacesMessage message =
                FacesUtils.createFacesMessage(bundleKey, FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
    }
}
