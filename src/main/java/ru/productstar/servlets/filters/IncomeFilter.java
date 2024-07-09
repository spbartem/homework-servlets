package ru.productstar.servlets.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.productstar.servlets.service.CheckLogin;

import java.io.IOException;

@WebFilter
public class IncomeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        var context = req.getServletContext();
        context.log("[IncomeFilter] doFilter");

        if (!CheckLogin.isAuthorized((HttpServletRequest) req, (HttpServletResponse) resp)) {return;}

        for (var k : req.getParameterMap().keySet()) {
            if (req.getParameter(k).equals("")) { throw new NullPointerException("Income is empty"); }
            if (Integer.parseInt(req.getParameter(k) ) < 0) {
                throw new IllegalArgumentException("Income must be greater than zero");
            }
        }

        chain.doFilter(req, resp);
    }
}
