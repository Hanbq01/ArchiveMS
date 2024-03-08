package com.dai.controller;

import com.dai.model.User;
import com.dai.service.RegisterService;
import com.dai.util.DBUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private RegisterService registerService;

    public void init() {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.registerService = new RegisterService(conn);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user = new User();
        user.setReal_name(request.getParameter("real_name"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setBirthdate(request.getParameter("birthdate"));
        user.setAddress(request.getParameter("address"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setJob(request.getParameter("job"));

        try {
            registerService.saveUser(user);
            response.getWriter().write("success");
        } catch (SQLException e) {
            response.getWriter().write("error");
            throw new ServletException(e);
        }
    }
}