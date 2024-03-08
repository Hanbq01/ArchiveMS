package com.dai.controller;

import com.dai.service.ArchiveCategoryManagementService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ArchiveCategoryManagementServlet")
public class ArchiveCategoryManagementServlet extends HttpServlet {
    private ArchiveCategoryManagementService archiveCategoryManagementService = new ArchiveCategoryManagementService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            String id = req.getParameter("id");
            try {
                archiveCategoryManagementService.deleteArchiveCategory(Integer.parseInt(id));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        req.setAttribute("archiveTypes", archiveCategoryManagementService.getAllArchiveTypes());
        req.getRequestDispatcher("archiveCategoryManagement.jsp").forward(req, resp);
    }
}