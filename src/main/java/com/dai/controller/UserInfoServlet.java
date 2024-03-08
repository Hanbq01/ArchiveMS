package com.dai.controller;

import com.dai.model.User;
import com.dai.service.UserInfoService;
import com.dai.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
    private UserInfoService userInfoService;

    public void init() {
        try {
            Connection conn = DBUtil.getConnection();
            this.userInfoService = new UserInfoService(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= (int) request.getSession().getAttribute("id");
        System.out.println(id);
        User userInfo = null;
        try {
            userInfo = userInfoService.getUser(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("user", userInfo);
        request.getRequestDispatcher("userInfo.jsp").forward(request, response);
    }
}