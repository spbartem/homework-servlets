package ru.productstar.servlets;

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

public class ExpensesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var context = req.getServletContext();
        context.log("[ExpensesServlet] doGet");

        if (!CheckLogin.isAuthorized(req, resp)) {return;}

        var transactions = new ArrayList<Transaction>((List) context.getAttribute("transactions"));

        int freeMoney = (int) context.getAttribute("freeMoney");
        for (var k : req.getParameterMap().keySet()) {
            if (req.getParameter(k).equals("")) { throw new NullPointerException("Expense is empty"); }
            int value = Integer.parseInt(req.getParameter(k));
            freeMoney -= value;
            transactions.add(new Transaction(k, value, TypeOfOpertion.EXPENSE));
        }

        context.setAttribute("freeMoney", freeMoney);
        context.setAttribute("transactions", transactions);
        resp.getWriter().println("Expenses were added");

        req.getRequestDispatcher("/summary").forward(req, resp);
    }
}
