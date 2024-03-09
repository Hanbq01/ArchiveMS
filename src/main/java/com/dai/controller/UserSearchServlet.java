package com.dai.controller;

import com.dai.model.User;
import com.dai.service.ManagementPersonnelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
    private ManagementPersonnelService service = new ManagementPersonnelService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String realName = request.getParameter("real_name");
        String idCard = request.getParameter("idCard");

        List<User> users = service.searchUsers(realName, idCard);

        request.setAttribute("users", users);
        request.getRequestDispatcher("managementPersonnel.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
