package com.dai.controller;

import com.dai.model.Applicant;
import com.dai.service.ApplicationManageService;
import com.dai.service.MyAppointmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@WebServlet("/ApplicationManagementServlet")
public class ApplicationManagementServlet extends HttpServlet {
    private ApplicationManageService service = new ApplicationManageService();
    private MyAppointmentService myAppointmentService = new MyAppointmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        List<Applicant> applicants = service.getAllOnlineAppointments();

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        for (Applicant applicant : applicants) {
            String reviewDateString = applicant.getReviewDate();
            if (reviewDateString != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime reviewDate = LocalDateTime.parse(reviewDateString, formatter);

                System.out.println(reviewDateString);

                // 检查是否超过15天
                long daysBetween = ChronoUnit.DAYS.between(reviewDate, now);
                if (daysBetween > 15) {
                    myAppointmentService.cancelAppointment(applicant.getId(), req);
                }
            } else {
                System.out.println("Review date is null for applicant with ID: " + applicant.getId());
            }
        }
        req.setAttribute("applicants", applicants);
        req.getRequestDispatcher("ApplicationManagement.jsp").forward(req, resp);
    }
}


