package com.java.framework.web;

import com.java.framework.exception.MoneyNotEnoughException;
import com.java.framework.exception.TransferException;
import com.java.framework.service.AccountService;
import com.java.framework.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Account Servlet
 * @author binjunkai
 * @version 1.0
 * @since 1.0
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get data
        String fromAct = request.getParameter("fromAct");
        String toAct = request.getParameter("toAct");
        double money = Double.parseDouble(request.getParameter("money"));
        String contextPath = request.getContextPath();
        //invoke business logic layer
        try {
            accountService.transfer(fromAct, toAct, money);
            //transfer succeed
            //invoke view layer
            response.sendRedirect(contextPath + "/success.html");
        } catch (MoneyNotEnoughException e) {
            response.sendRedirect(contextPath + "/insufficient.html");
        } catch (TransferException e) {
            response.sendRedirect(contextPath + "/error.html");
        } catch (Exception e) {
            response.sendRedirect(contextPath + "/error.html");
        }

    }
}
