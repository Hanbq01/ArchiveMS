package com.dai.controller;

import com.dai.model.Applicant;
import com.dai.model.User;
import com.dai.service.ApplicantService;
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
import java.lang.ref.ReferenceQueue;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/ApplicantServlet")
@MultipartConfig
public class ApplicantServlet extends HttpServlet {
    private ApplicantService applicantService;

    public void init() {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.applicantService = new ApplicantService(conn);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user = (User) request.getSession().getAttribute("user"); // 获取当前登录的用户
        String realname = user.getReal_name(); // 获取 realname

        Applicant applicant = new Applicant();
        applicant.setName(request.getParameter("name"));
        applicant.setIdCard(request.getParameter("id_card"));
        applicant.setPhone(request.getParameter("phone"));
        applicant.setNativePlace(request.getParameter("native_place"));
        applicant.setQueryPurpose(request.getParameter("query_purpose"));
        applicant.setQueriedName(request.getParameter("queried_name"));
        applicant.setQueriedProfession(request.getParameter("queried_profession"));
        applicant.setApplicants(realname);

        // 处理图片上传
        Part filePart = request.getPart("image"); // 获取上传的文件
        String fileName = filePart.getSubmittedFileName();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss")); // 获取文件名
        fileName = now + suffix;
        Path uploads = Path.of(getServletContext().getRealPath(""), "img/pic/"); // 设置上传目录
        Files.createDirectories(uploads); // 确保上传目录存在
        try (InputStream input = filePart.getInputStream()) {
            Path filePath = uploads.resolve(fileName); // 创建文件路径
            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING); // 将文件保存到服务器
            applicant.setImagePath(fileName); // 将文件路径保存到数据库
        }

        applicant.setNotes(request.getParameter("notes"));
        applicant.setReviewDate(now);

        try {
            applicantService.saveApplicant(applicant);
            response.getWriter().write("success");
        } catch (SQLException e) {
            response.getWriter().write("error");
            throw new ServletException(e);
        }
    }
}
