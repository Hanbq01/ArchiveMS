package com.dai.service;

import com.dai.model.Applicant;
import com.dai.util.DBUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MyAppointmentService {
    public List<Applicant> getAllAppointments(String realname) {
        List<Applicant> applicants = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
//            System.out.println(realname);        // 测试一下能不能获取
            String sql = "SELECT * FROM applicant WHERE applicants = '" + realname + "'";
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
                applicant.setReviewNote(rs.getString("review_note"));
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

    public void cancelAppointment(int id, HttpServletRequest req) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();

            // 先查询出图片路径
            String querySql = "SELECT image_path FROM applicant WHERE id = " + id;
            rs = stmt.executeQuery(querySql);
            if (rs.next()) {
                String img = rs.getString("image_path");
                String realPath = req.getServletContext().getRealPath("/");
                String imagePath = realPath + "img/pic/" + img;
                if (imagePath != null && !imagePath.isEmpty()) {
                    // 如果图片路径不为空，则删除图片
                    File file = new File(imagePath);
                    if (file.exists() && file.isFile()) {
                        if (file.delete()) {
                            System.out.println("delete success!");
                        } else {
                            System.out.println("delete fail!");
                        }
                    } else {
                        System.out.println("no file exist");
                    }
                }
            }

            // 删除数据库记录
            String deleteSql = "DELETE FROM applicant WHERE id = " + id;
            stmt.executeUpdate(deleteSql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
