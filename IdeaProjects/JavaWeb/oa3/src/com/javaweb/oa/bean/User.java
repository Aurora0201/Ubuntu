package com.javaweb.oa.bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {
    String name;
    String password;

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        Object obj = application.getAttribute("count");
        if (obj == null) {
            application.setAttribute("count", 1);
        } else {
            int count = ((Integer) obj);
            count++;
            application.setAttribute("count",count);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        Object obj = application.getAttribute("count");
        int count = ((Integer) obj);
        count--;
        application.setAttribute("count",count);
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
