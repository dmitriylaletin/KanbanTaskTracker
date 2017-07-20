package com.eisgroup.tasktracker.servlets;

import com.eisgroup.tasktracker.exceptions.LoginException;
import com.eisgroup.tasktracker.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 10:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/TestContext.xml",})
public class AutoLoginServletTest {
    private static final String INDEX_PAGE = "/index.xhtml";
    private static final String CONTEXT_PATH = "context";

    @Test
    public void testWithPrincipals() throws LoginException, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getUserPrincipal()).thenReturn(mock(Principal.class));
        when(request.getContextPath()).thenReturn(CONTEXT_PATH);

        AutoLoginServlet autoLoginServlet = new AutoLoginServlet();
        autoLoginServlet.doGet(request, response);

        verify(response).sendRedirect(CONTEXT_PATH + INDEX_PAGE);
    }

    @Test
    public void testWithoutUuid() throws LoginException, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getUserPrincipal()).thenReturn(null);
        when(request.getContextPath()).thenReturn(CONTEXT_PATH);

        AutoLoginServlet autoLoginServlet = new AutoLoginServlet();
        autoLoginServlet.doGet(request, response);

        verify(response).sendRedirect(CONTEXT_PATH + INDEX_PAGE);
    }

    @Test(expected = NullPointerException.class)
    public void testLoginCall() throws LoginException, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getUserPrincipal()).thenReturn(null);
        when(request.getContextPath()).thenReturn(CONTEXT_PATH);
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie(Constants.UUID, "test_uuid")});

        AutoLoginServlet autoLoginServlet = new AutoLoginServlet();
        autoLoginServlet.doGet(request, response);
        // expected NPE when call AuthManager
    }
}
