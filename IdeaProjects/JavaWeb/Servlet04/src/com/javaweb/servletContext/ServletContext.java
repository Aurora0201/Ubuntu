package com.javaweb.servletContext;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ServletContext extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        jakarta.servlet.ServletContext application = getServletContext();

        out.print("ServletContext Object:" + application + "<br>");

        Enumeration<String> names = application.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = application.getInitParameter(name);
            out.print(name + " " + value);
        }
    }
}
