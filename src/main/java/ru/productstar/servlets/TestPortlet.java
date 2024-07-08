package ru.productstar.servlets;

import javax.portlet.*;
import java.io.IOException;

public class TestPortlet extends GenericPortlet {
    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, PortletSecurityException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>Заголовок портлета</h1>");
        response.getWriter().println("<p>Текст портлета</p>");
    }
}
