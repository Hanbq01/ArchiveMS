package com.dai.controller;

import com.dai.model.ArchiveType;
import com.dai.service.ArchiveCategoryManagementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/UpdateArchiveCategoryServlet")
public class UpdateArchiveCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArchiveCategoryManagementService service = new ArchiveCategoryManagementService();
        try {
            ArchiveType archiveType = new ArchiveType();
            archiveType.setId(Integer.parseInt(request.getParameter("id")));
            archiveType.setTypeName(request.getParameter("typeName"));
            archiveType.setTypeId(request.getParameter("typeId"));

            LocalDateTime now = LocalDateTime.now();
            // 格式化日期和时间
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedNow = now.format(formatter);

            archiveType.setCreateDate(formattedNow);
            archiveType.setNotes(request.getParameter("notes"));

            service.updateArchiveCategory(archiveType);
            response.sendRedirect("ArchiveCategoryManagementServlet");
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