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

@WebServlet("/ArchiveFilingServlet")
public class ArchiveFilingServlet extends HttpServlet {
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
            } else if ("restore".equals(action)) {//如果点击还原按钮
                try {
                    archiveService.restoreArchive(Integer.parseInt(id));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            List<Archive> archives = archiveService.getArchives(1);//filing设为1 显示已经归档的档案
            req.setAttribute("archives", archives);
            req.getRequestDispatcher("archiveFilingManagement.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
