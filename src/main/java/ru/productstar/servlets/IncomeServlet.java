package ru.productstar.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.productstar.servlets.model.Transaction;
import ru.productstar.servlets.model.TypeOfOpertion;
import ru.productstar.servlets.service.CheckLogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/income/add")
public class IncomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var context = req.getServletContext();
        context.log("[IncomeServlet] doGet");
            if (!CheckLogin.isAuthorized(req, resp)) {
                return;
            }

            var transactions = new ArrayList<Transaction>((List) context.getAttribute("transactions"));

            int freeMoney = (int) context.getAttribute("freeMoney");
            for (var k : req.getParameterMap().keySet()) {
                if (req.getParameter(k).equals("")) { throw new NullPointerException("Income is empty"); }
                int value = Integer.parseInt(req.getParameter(k));
                freeMoney += value;
                transactions.add(new Transaction(k, value, TypeOfOpertion.PROFIT));
            }

            context.setAttribute("freeMoney", freeMoney);
            context.setAttribute("transactions", transactions);
            resp.getWriter().println("Incomes were added");

            req.getRequestDispatcher("/summary").forward(req, resp);
    }
}
