package com.emotion.analysis.web;

import com.alibaba.fastjson2.JSON;
import com.emotion.analysis.bean.Content;
import com.emotion.analysis.service.ContentService;
import com.emotion.analysis.service.impl.ContentServiceImpl;
import com.emotion.analysis.util.DataParseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List<Content> contents = contentService.getAllContent();
        String jsonString = JSON.toJSONString(contents);
        out.print(jsonString);
    }*/

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
        List<Content> contents = contentService.getAllContent();
        String jsonString = JSON.toJSONString(contents);
        out.print(jsonString);
    }

    /**
     * get the json from web
     * @param request request object
     * @param response response object
     */
    public void saveList(HttpServletRequest request, HttpServletResponse response) {
        String json = request.getParameter("json");
        DataParseUtil.parseJSON(json);
    }
}
