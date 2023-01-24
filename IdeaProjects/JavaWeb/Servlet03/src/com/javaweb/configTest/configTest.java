package com.javaweb.configTest;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class configTest extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

       /* ServletConfig config = getServletConfig();
        Enumeration<String> names = config.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String param = config.getInitParameter(name);
            out.print(name + " : " + param + "<br>");
        }*/

        Enumeration<String> names = getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = getInitParameter(name);
            out.print(name + " : " + value + "<br>");
        }

    }
}
