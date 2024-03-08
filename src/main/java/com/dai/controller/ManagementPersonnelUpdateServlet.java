package com.dai.controller;

import com.dai.model.User;
import com.dai.service.ManagementPersonnelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ManagementPersonnelUpdateServlet")
public class ManagementPersonnelUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            // 获取表单数据
            int id = Integer.parseInt(request.getParameter("id"));
            String real_name = request.getParameter("realName");
            String username = request.getParameter("username");
            String birthdate = request.getParameter("birthdate");
            String idCard = request.getParameter("idCard");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String job = request.getParameter("job");

            // 创建一个User对象，并设置其属性
            User user = new User();
            user.setId(id);
            user.setReal_name(real_name);
            user.setUsername(username);
            user.setBirthdate(birthdate);
            user.setIdCard(idCard);
            user.setAddress(address);
            user.setEmail(email);
            user.setPhone(phone);
            user.setJob(job);

            ManagementPersonnelService service = new ManagementPersonnelService();
            service.updatePerson(user);
            response.sendRedirect("ManagementPersonnelServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
