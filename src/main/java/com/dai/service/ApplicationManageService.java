package com.dai.service;

import com.dai.model.Applicant;
import com.dai.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ApplicationManageService {
    public List<Applicant> getAllOnlineAppointments() {
        List<Applicant> applicants = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM applicant";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Applicant applicant = new Applicant();
                applicant.setId(rs.getInt("id"));
                applicant.setName(rs.getString("name"));
                applicant.setIdCard(rs.getString("id_card"));
                applicant.setPhone(rs.getString("phone"));
                applicant.setNativePlace(rs.getString("native_place"));
                applicant.setQueryPurpose(rs.getString("query_purpose"));
                applicant.setQueriedName(rs.getString("queried_name"));
                applicant.setQueriedProfession(rs.getString("queried_profession"));
                applicant.setImagePath(rs.getString("image_path"));
                applicant.setAppointment_date(rs.getString("Appointment_date"));
                applicant.setNotes(rs.getString("notes"));
                applicant.setRole(rs.getInt("role"));
                applicant.setReview(rs.getInt("review"));
                applicant.setReviewDate(rs.getString("review_date"));
                applicants.add(applicant);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applicants;
    }

    public void reviewApplication(int id, String action, String reviewNote) throws Exception {
        String sql = "UPDATE applicant SET review = ?,  review_note = ?,review_date=? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, "approve".equals(action) ? 1 : 2);
            stmt.setString(2, reviewNote); // 保存审核意见

            LocalDateTime now = LocalDateTime.now();
            // 格式化日期和时间
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedNow = now.format(formatter);
            stmt.setString(3, formattedNow);

            stmt.setInt(4, id);

            stmt.executeUpdate();
        }
    }
}
