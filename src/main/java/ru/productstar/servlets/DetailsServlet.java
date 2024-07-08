package ru.productstar.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.productstar.servlets.model.Transaction;
import ru.productstar.servlets.service.CheckLogin;

import java.io.IOException;
import java.util.List;

public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var context = req.getServletContext();
        context.log("[DetailsServlet] doGet");

        if (!CheckLogin.isAuthorized(req, resp)) {return;}

        //resp.getWriter().println("Expenses: ");
        for (Transaction e: (List<Transaction>)context.getAttribute("transactions")) {
            resp.getWriter().println(String.format("%s: %s(%d)", e.getOperation().getName(), e.getName(), e.getSum()));
        }
        resp.getWriter().println("\n");
    }
}
