package com.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class Bservlet implements Servlet{
    public Bservlet() {
        System.out.println("B done");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
