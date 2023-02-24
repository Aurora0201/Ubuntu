package com.emotion.analysis.web;

import com.alibaba.fastjson2.JSON;
import com.emotion.analysis.bean.Content;
import com.emotion.analysis.service.ContentService;
import com.emotion.analysis.service.impl.ContentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Content Servlet
 * send the json to web
 * @author binjunkai
 * @since 1.0
 * @version 1.0
 */
@WebServlet({"/getList","/saveList"})
public class ContentServlet extends HttpServlet {
    ContentService contentService = new ContentServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/getList".equals(path)) {
            getList(request, response);
        } else {
            saveList(request, response);
        }
    }

    /**
     * response json to web
     * @param request request object
     * @param response response object
     */
    public void getList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List<Content> contents = contentService.getAllContents();
        String jsonString = JSON.toJSONString(contents);
        out.print(jsonString);
    }

    /**
     * get the json from web
     * @param request request object
     * @param response response object
     */
    public void saveList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String json = request.getParameter("json");
        int count = contentService.saveContents(json);
        String retJson = "{'count':" + count + "}";
        out.print(count);
    }
}
