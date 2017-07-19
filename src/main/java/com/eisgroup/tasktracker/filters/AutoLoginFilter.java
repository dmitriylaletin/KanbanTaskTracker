package com.eisgroup.tasktracker.filters;

import com.eisgroup.tasktracker.utils.Constants;
import com.eisgroup.tasktracker.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:16
 */
public class AutoLoginFilter implements Filter {
    private static final String AUTOLOGIN_PAGE = "autologin.xhtml";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getUserPrincipal() == null) {
            String uuid = ServletUtils.getCookieValue(request, Constants.UUID);
            if (uuid != null) {
                request.getRequestDispatcher(AUTOLOGIN_PAGE).forward(request, response);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
