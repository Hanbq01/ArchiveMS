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

@WebServlet("/UpdateArchiveServlet")
public class UpdateArchiveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Archive archive = new Archive();
            User user = (User) request.getSession().getAttribute("user"); // 获取当前登录的用户
            String realname = user.getReal_name(); // 获取 realname
            archive.setName(request.getParameter("name"));
            archive.setYear(request.getParameter("year"));
            archive.setNotes(request.getParameter("notes"));
            archive.setOwner(realname);

            MyArchiveService service = new MyArchiveService();
            service.updateArchive(archive);
            response.getWriter().write("success");
        } catch (Exception e) {
            response.getWriter().write("error");
            throw new ServletException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
