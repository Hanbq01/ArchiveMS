package com.dai.controller;

import com.dai.model.User;
import com.dai.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(username, password, 1);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("id",user.getId());
            resp.sendRedirect("index.jsp");
        } else {
            req.getSession().setAttribute("error", "无效的用户名或密码");
            resp.sendRedirect("login.jsp");
        }
    }
}