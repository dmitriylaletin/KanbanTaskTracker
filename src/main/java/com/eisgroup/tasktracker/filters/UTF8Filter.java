package com.eisgroup.tasktracker.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:18
 */
public class UTF8Filter implements Filter {
    private static final String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
