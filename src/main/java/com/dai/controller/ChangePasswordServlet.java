package com.dai.controller;

import com.dai.service.UserInfoService;
import com.dai.util.DBUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        int id = (int) request.getSession().getAttribute("id");
        String newPassword = request.getParameter("newPassword");
        try (Connection conn = DBUtil.getConnection()) {
            UserInfoService userInfoService = new UserInfoService(conn);
            userInfoService.changePassword(id, newPassword);
            response.getWriter().write("密码修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}