package com.dai.controller;

import com.dai.model.Archive;
import com.dai.model.User;
import com.dai.service.MyArchiveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/myArchiveServlet")
public class MyArchiveServlet extends HttpServlet {

    private MyArchiveService myArchiveService = new MyArchiveService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            User user = (User) req.getSession().getAttribute("user"); // 获取当前登录的用户
            String realname = user.getReal_name(); // 获取 realname
            List<Archive> archives = myArchiveService.getAllArchives(realname);
            req.setAttribute("archives", archives);
            req.getRequestDispatcher("myArchive.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
