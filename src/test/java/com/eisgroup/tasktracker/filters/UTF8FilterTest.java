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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 10:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/TestContext.xml",})
public class UTF8FilterTest {
    private static final String ENCODING = "UTF-8";

    @Test
    public void tesEncodingSet() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        final UTF8Filter utf8Filter = new UTF8Filter();
        utf8Filter.doFilter(request, response,
                filterChain);

        verify(request).setCharacterEncoding(ENCODING);
        verify(response).setCharacterEncoding(ENCODING);
    }
}
