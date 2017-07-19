package com.eisgroup.tasktracker.servlets;

import com.eisgroup.tasktracker.exceptions.LoginException;
import com.eisgroup.tasktracker.service.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:35
 */
public class AutoLoginServlet extends HttpServlet {
    private static final String INDEX_PAGE = "/index.xhtml";

    @Autowired
    AuthManager authManager;

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check user not logged in
        if (request.getUserPrincipal() != null) {
            response.sendRedirect(request.getContextPath() + INDEX_PAGE);
            return;
        }

        // checking if uuid cookie present
        String uuid = ServletUtils.getCookieValue(request, Constants.UUID);
        if (uuid == null) {
            response.sendRedirect(request.getContextPath() + INDEX_PAGE);
            return;
        }

        try {
            authManager.loginFromServlet(uuid, request);
        } catch (LoginException e) {
            ServletUtils.removeCookie(response, Constants.UUID);
        }

        response.sendRedirect(request.getContextPath() + INDEX_PAGE);
    }
}
