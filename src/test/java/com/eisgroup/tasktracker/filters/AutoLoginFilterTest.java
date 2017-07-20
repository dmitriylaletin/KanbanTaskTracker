package com.eisgroup.tasktracker.filters;

import com.eisgroup.tasktracker.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 10:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/TestContext.xml",})
public class AutoLoginFilterTest {
    private static final String AUTOLOGIN_PAGE = "autologin.xhtml";

    @Test
    public void testWithPrincipals() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(request.getUserPrincipal()).thenReturn(new Principal() {
            @Override
            public String getName() {
                return "test";
            }
        });

        final AutoLoginFilter autoLoginFilter = new AutoLoginFilter();
        autoLoginFilter.doFilter(request, response,
                filterChain);

        verify(request, never()).getCookies();
    }

    @Test
    public void testNoPrincipalsNoCookies() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(request.getUserPrincipal()).thenReturn(null);
        when(request.getCookies()).thenReturn(new Cookie[]{});

        final AutoLoginFilter autoLoginFilter = new AutoLoginFilter();
        autoLoginFilter.doFilter(request, response,
                filterChain);

        verify(request, never()).getRequestDispatcher(AUTOLOGIN_PAGE);
    }

    @Test
    public void testNoPrincipalsWithCookies() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getUserPrincipal()).thenReturn(null);
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie(Constants.UUID, "test-uuid")});
        when(request.getRequestDispatcher(AUTOLOGIN_PAGE)).thenReturn(requestDispatcher);

        final AutoLoginFilter autoLoginFilter = new AutoLoginFilter();
        autoLoginFilter.doFilter(request, response,
                filterChain);

        verify(request).getRequestDispatcher(AUTOLOGIN_PAGE);
        verify(requestDispatcher).forward(request, response);
    }
}
