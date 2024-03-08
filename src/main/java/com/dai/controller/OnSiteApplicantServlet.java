package com.dai.controller;

import com.dai.model.Applicant;
import com.dai.model.User;
import com.dai.service.ApplicantService;
import com.dai.service.OnSiteApplicantService;
import com.dai.util.DBUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MultipartConfig
@WebServlet("/OnSiteApplicantServlet")
public class OnSiteApplicantServlet extends HttpServlet {
    private OnSiteApplicantService onSiteApplicantService;

    public void init() {
        try {
            Connection conn = DBUtil.getConnection();
            if (conn != null) {
                this.onSiteApplicantService = new OnSiteApplicantService(conn);
            } else {
                // 处理conn为null的情况，例如抛出一个异常或者记录一个错误日志
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        applicant.setAppointment_date(request.getParameter("appointmentTime"));
        applicant.setNotes(request.getParameter("notes"));
        applicant.setApplicants(realname);

        LocalDateTime now = LocalDateTime.now();
        // 格式化日期和时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        applicant.setReviewDate(formattedNow);

        try {
            onSiteApplicantService.saveOnsiteApplicant(applicant);
            response.getWriter().write("success");
        } catch (SQLException e) {
            response.getWriter().write("error");
            throw new ServletException(e);
        }
    }
}
