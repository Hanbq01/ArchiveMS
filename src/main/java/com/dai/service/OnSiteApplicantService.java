package com.dai.service;

import com.dai.model.Applicant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OnSiteApplicantService {
    private Connection conn;

    public OnSiteApplicantService(Connection conn) {
        this.conn = conn;
    }

    public void saveOnsiteApplicant(Applicant applicant) throws SQLException {
        String sql = "INSERT INTO applicant (name, id_card, phone, native_place, query_purpose, queried_name, queried_profession, Appointment_date, notes,applicants,role,review_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";//1为到馆预约，0为在线预约
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, applicant.getName());
        stmt.setString(2, applicant.getIdCard());
        stmt.setString(3, applicant.getPhone());
        stmt.setString(4, applicant.getNativePlace());
        stmt.setString(5, applicant.getQueryPurpose());
        stmt.setString(6, applicant.getQueriedName());
        stmt.setString(7, applicant.getQueriedProfession());
        stmt.setString(8, applicant.getAppointment_date());
        stmt.setString(9, applicant.getNotes());
        stmt.setString(10, applicant.getApplicants());
        stmt.setString(11, "1");
        stmt.setString(12, applicant.getReviewDate());
        stmt.executeUpdate();
    }
}
