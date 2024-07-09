package ru.productstar.servlets.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CheckLogin {
    public static boolean isAuthorized(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var session = req.getSession(false);
        if (session == null) {
            resp.getWriter().println("Not authorized");
            return false;
        }
        return true;
    }
}
