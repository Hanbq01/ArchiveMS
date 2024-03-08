package com.dai.controller;

import com.dai.model.Archive;
import com.dai.service.ArchiveService;
import com.dai.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MultipartConfig
@WebServlet("/AddArchiveServlet")
public class AddArchiveServlet extends HttpServlet {
    private ArchiveService ArchiveService;

    public void init() {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.ArchiveService = new ArchiveService(conn);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Archive archive = new Archive();
        archive.setName(request.getParameter("name"));
        archive.setYear(request.getParameter("year"));
        archive.setOwner(request.getParameter("owner"));
        archive.setIdcard(request.getParameter("idcard"));
        archive.setType(request.getParameter("type"));

        String name = archive.getOwner();//获取owner输入的姓名

        Part filePart = request.getPart("file_path");
        String fileName = filePart.getSubmittedFileName();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = name + "档案" + suffix;//文件名命名为 姓名+档案
        Path uploads = Path.of(getServletContext().getRealPath(""), "file/"); // 设置上传目录
        Files.createDirectories(uploads); // 确保上传目录存在
        try (InputStream input = filePart.getInputStream()) {
            Path filePath = uploads.resolve(fileName); // 创建文件路径
            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING); // 将文件保存到服务器
            archive.setFilePath(fileName); // 将文件路径保存到数据库
        }

        LocalDateTime now = LocalDateTime.now();
        // 格式化日期和时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        archive.setCreateDate(formattedNow);
        archive.setNotes(request.getParameter("notes"));


        try {
            ArchiveService.addArchive(archive);
            response.getWriter().write("success");
        } catch (SQLIntegrityConstraintViolationException e) { //判断是否违反外键约束
            response.getWriter().write("fkerror");
        } catch (Exception e) {
            response.getWriter().write("error");
            throw new ServletException(e);
        }
    }
}
