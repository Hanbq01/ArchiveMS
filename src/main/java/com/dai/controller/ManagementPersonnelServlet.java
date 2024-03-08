package com.dai.controller;

import com.dai.model.User;
import com.dai.service.ManagementPersonnelService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ManagementPersonnelServlet")
public class ManagementPersonnelServlet extends HttpServlet {
    private ManagementPersonnelService managementPersonnelService = new ManagementPersonnelService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            String id = req.getParameter("id");
            String realName = req.getParameter("realName");
            managementPersonnelService.deleteUser(Integer.parseInt(id), realName);
        }
        req.setAttribute("users", managementPersonnelService.getAllUsers());
        req.getRequestDispatcher("managementPersonnel.jsp").forward(req, resp);
    }
}
