package com.dai.controller;

import com.dai.model.User;
import com.dai.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminLoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 注意这里，我们将角色设置为0，表示管理员
        User user = userService.login(username, password, 0);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("admin_index.jsp");
        } else {
            req.getSession().setAttribute("error", "无效的用户名或密码");
            resp.sendRedirect("admin_login.jsp");
        }
    }
}

