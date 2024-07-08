package ru.productstar.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.productstar.servlets.model.Transaction;
import ru.productstar.servlets.model.TypeOfOpertion;
import ru.productstar.servlets.service.CheckLogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SummaryServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        var context = config.getServletContext();
        context.log("[SummaryServlet] init");

        var salary = Integer.parseInt(context.getInitParameter("salary"));
        var rent = Integer.parseInt(config.getInitParameter("rent"));

        context.setAttribute("freeMoney", salary - rent);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("rent", rent, TypeOfOpertion.EXPENSE));
        context.setAttribute("transactions", transactions);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var context = req.getServletContext();
        context.log("[SummaryServlet] doGet");

        if (!CheckLogin.isAuthorized(req, resp)) {return;}

        req.getRequestDispatcher("/details").include(req, resp);
        resp.getWriter().println("Free money: " + context.getAttribute("freeMoney"));
    }
}
