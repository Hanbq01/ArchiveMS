package com.dai.controller;

import com.dai.service.ApplicationManageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");
        String reviewNote = request.getParameter("reviewNote");
        ApplicationManageService service = new ApplicationManageService();
        try {
            service.reviewApplication(id, action, reviewNote);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("ApplicationManagement.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
