package top.pi1grim.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/test")
public class RedirectTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long begin = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - begin > 5 * 1000) {
                break;
            }
        }

        response.sendRedirect("view.html");
    }
}
