package com.dai.controller;

import com.dai.model.Applicant;
import com.dai.model.User;
import com.dai.service.MyAppointmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/myAppointmentServlet")
public class MyAppointmentServlet extends HttpServlet {
    private MyAppointmentService service = new MyAppointmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("cancel".equals(action)) {
            String id = req.getParameter("id");
            service.cancelAppointment(Integer.parseInt(id), req);
        } else {
            User user = (User) req.getSession().getAttribute("user");
            String realname = user.getReal_name();
            List<Applicant> applicants = service.getAllAppointments(realname);
            req.setAttribute("applicants", applicants);
            req.getRequestDispatcher("myAppointment.jsp").forward(req, resp);
        }
    }

}
