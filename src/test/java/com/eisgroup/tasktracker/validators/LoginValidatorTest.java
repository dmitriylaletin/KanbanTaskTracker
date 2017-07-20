package com.eisgroup.tasktracker.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 10:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/TestContext.xml",})
public class LoginValidatorTest {
    @Test
    public void testNormalLogin() {
        LoginValidator loginValidator = testLogin("normalLogin");
        verify(loginValidator, never()).returnMessage(anyString());
    }

    @Test
    public void testLowLength() {
        LoginValidator loginValidator = testLogin("a");
        verify(loginValidator).returnMessage("login_length_error");
    }

    @Test
    public void testHighLength() {
        LoginValidator loginValidator = testLogin("LongLogin0000000000000000000");
        verify(loginValidator).returnMessage("login_length_error");
    }

    @Test
    public void testNoFirstLetter() {
        LoginValidator loginValidator = testLogin("7login");
        verify(loginValidator).returnMessage("login_first_letter_error");
    }

    @Test
    public void testSpecialCharacters() {
        LoginValidator loginValidator = testLogin("log|n");
        verify(loginValidator).returnMessage("login_special_characters");
    }

    private LoginValidator testLogin(String login) {
        LoginValidator loginValidator = mock(LoginValidator.class, CALLS_REAL_METHODS);
        doNothing().when(loginValidator).returnMessage(anyString());
        doNothing().when(loginValidator).checkLoginExists(anyString());
        loginValidator.validate(null, null, login);
        return loginValidator;
    }
}
