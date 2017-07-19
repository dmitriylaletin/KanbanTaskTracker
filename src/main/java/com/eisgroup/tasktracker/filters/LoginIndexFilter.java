package com.eisgroup.tasktracker.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:17
 */
public class LoginIndexFilter implements Filter {
    private static final String INNER_PAGE = "/pages/index.xhtml";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (request.getUserPrincipal() != null) {
            response.sendRedirect(request.getContextPath() + INNER_PAGE);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}
