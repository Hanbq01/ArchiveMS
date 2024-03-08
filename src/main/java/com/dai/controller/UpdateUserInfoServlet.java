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

@WebServlet("/UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            // 获取表单数据
            String real_name = request.getParameter("real_name");
            String username = request.getParameter("username");
            String birthdate = request.getParameter("birthdate");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String job = request.getParameter("job");

            // 创建一个User对象，并设置其属性
            User user = new User();
            user.setReal_name(real_name);
            user.setUsername(username);
            user.setBirthdate(birthdate);
            user.setAddress(address);
            user.setEmail(email);
            user.setPhone(phone);
            user.setJob(job);

            // 从session中获取当前登录用户的id
            int id = (int) request.getSession().getAttribute("id");
            user.setId(id);

            // 获取数据库连接
            Connection conn = DBUtil.getConnection();

            // 创建一个UserInfoService对象，并调用其updateUser方法来更新用户信息
            UserInfoService userInfoService = new UserInfoService(conn);
            userInfoService.updateUser(user);

            // 重定向用户到userinfo.jsp页面
            response.sendRedirect("UserInfoServlet");
        } catch (Exception e) {
            // 在抛出ServletException时，添加异常信息
            throw new ServletException("Error updating user information: " + e.getMessage(), e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
