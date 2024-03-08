package com.dai.controller;

import com.dai.model.User;
import com.dai.service.UserInfoService;
import com.dai.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private UserInfoService userInfoService;

    public void init() {
        try {
            Connection conn = DBUtil.getConnection();
            this.userInfoService = new UserInfoService(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("head"); // 获取文件
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // 获取文件名
        String extension = fileName.substring(fileName.lastIndexOf(".")); // 获取文件扩展名

        // 检查文件扩展名是否为 .jpg 或 .png
        if (!extension.equals(".jpg") && !extension.equals(".png")) {
            throw new ServletException("只允许上传 .jpg 或 .png 格式的文件");
        }

        String realName = ((User) request.getSession().getAttribute("user")).getReal_name(); // 获取真实姓名
        int id = (int) request.getSession().getAttribute("id");//获取id
        String uploadPath = getServletContext().getRealPath("/img/head/") + realName + id + extension; // 设置上传路径

        // 删除旧的头像文件
        File oldFile = new File(uploadPath);
        if (oldFile.exists()) {
            oldFile.delete();
        }

        // 保存新的头像文件
        filePart.write(uploadPath);

        // 更新用户的 head_path
        try {
            userInfoService.updateHeadPath(id, "img/head/" + realName + id + extension);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 重定向到用户信息页面
        response.getWriter().write("success");
    }

}
