package com.javaweb.bank.web;

import com.javaweb.bank.exceptions.MoneyNotEnoughException;
import com.javaweb.bank.service.AccountService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Account service Servlet
 * @author binjunkai
 * @version 2.0
 * @since 1.0
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {
    AccountService accountService = new AccountService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fromAct = request.getParameter("fromAct");
        String toAct = request.getParameter("toAct");
        double money = Double.parseDouble(request.getParameter("money"));

        try {
            accountService.transfer(fromAct, toAct, money);
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        }  catch (MoneyNotEnoughException e) {
            response.sendRedirect(request.getContextPath() + "/MoneyNotEnough.jsp");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/failed.jsp");
        }

    }
}
