package com.dai.controller;

import com.dai.model.Archive;
import com.dai.service.ArchiveService;
import com.dai.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/ArchiveManagementServlet")
public class ArchiveManagementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            Connection conn = DBUtil.getConnection();
            ArchiveService archiveService = new ArchiveService(conn);
            String action = req.getParameter("action");
            String id = req.getParameter("id");
            if ("delete".equals(action)) {//如果点击删除按钮
                try {
                    archiveService.deleteArchive(Integer.parseInt(id), req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if ("filing".equals(action)) {//如果点击归档按钮
                try {
                    archiveService.filingArchive(Integer.parseInt(id));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            List<Archive> archives = archiveService.getArchives(0);//这里设为0 显示未归档的档案
            req.setAttribute("archives", archives);
            req.getRequestDispatcher("archiveManagement.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
