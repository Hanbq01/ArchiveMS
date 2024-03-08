package com.dai.service;

import com.dai.model.Applicant;

import java.sql.*;

public class ApplicantService {
    private Connection conn;

    public ApplicantService(Connection conn) {
        this.conn = conn;
    }

    public void saveApplicant(Applicant applicant) throws SQLException {
        String sql = "INSERT INTO applicant (name, id_card, phone, native_place, query_purpose, queried_name, queried_profession, image_path, notes, applicants,role,review_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";//0为在线预约
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, applicant.getName());
        stmt.setString(2, applicant.getIdCard());
        stmt.setString(3, applicant.getPhone());
        stmt.setString(4, applicant.getNativePlace());
        stmt.setString(5, applicant.getQueryPurpose());
        stmt.setString(6, applicant.getQueriedName());
        stmt.setString(7, applicant.getQueriedProfession());
        stmt.setString(8, applicant.getImagePath());
        stmt.setString(9, applicant.getNotes());
        stmt.setString(10, applicant.getApplicants());
        stmt.setString(11,"0");
        stmt.setString(12,applicant.getReviewDate());
        stmt.executeUpdate();
    }
}