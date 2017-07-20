package com.eisgroup.tasktracker.filters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

import static org.mockito.Mockito.*;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 10:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/TestContext.xml",})
public class LoginIndexFilterTest {
    private static final String INNER_PAGE = "/pages/index.xhtml";

    @Test
    public void tesNullPrincipals() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(request.getUserPrincipal()).thenReturn(null);

        final LoginIndexFilter loginIndexFilter = new LoginIndexFilter();
        loginIndexFilter.doFilter(request, response,
                filterChain);

        verifyZeroInteractions(response);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testWithPrincipals() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(request.getUserPrincipal()).thenReturn(mock(Principal.class));
        final String context = "context";
        when(request.getContextPath()).thenReturn(context);

        final LoginIndexFilter loginIndexFilter = new LoginIndexFilter();
        loginIndexFilter.doFilter(request, response,
                filterChain);

        verify(response).sendRedirect(context + INNER_PAGE);
    }
}
