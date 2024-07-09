package ru.productstar.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static jakarta.servlet.RequestDispatcher.*;

@WebServlet(urlPatterns = "/errorHandler")
public class ErrorHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html><head><title>Error</title>" +
                    "<link rel=\"stylesheet\" href=\"../styles.css\"></head><body>");
            writer.write("<img src=\"../images/monkey.png\" alt=\"Monkey\">");
            writer.write("<b>Error (" + req.getAttribute(ERROR_STATUS_CODE) + ")</b> - "
                    + req.getAttribute(ERROR_MESSAGE));
            writer.write("</html></body>");
        }
    }
}
