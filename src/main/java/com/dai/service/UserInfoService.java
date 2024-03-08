package com.dai.service;

import com.dai.model.User;
import com.dai.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoService {
    private Connection conn;

    public UserInfoService(Connection conn) {
        this.conn = conn;
    }

    public User getUser(int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(id));
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setReal_name(rs.getString("real_name"));
            user.setUsername(rs.getString("username"));
            user.setBirthdate(rs.getString("birthdate"));
            user.setAddress(rs.getString("address"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            user.setJob(rs.getString("job"));
            user.setHead_path(rs.getString("head_path"));
            user.setIdCard(rs.getString("idcard"));
            return user;
        }

        return null;
    }

    public void updateHeadPath(int id, String headPath) throws SQLException {
        String updateSql = "UPDATE user SET head_path = ? WHERE id = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateSql);
        updateStmt.setString(1, headPath);
        updateStmt.setString(2, String.valueOf(id));
        updateStmt.executeUpdate();
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET real_name = ?,username = ?, birthdate = ?, address = ?, email = ?, phone = ?, job = ?, idcard=? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getReal_name());
        stmt.setString(2, user.getUsername());
        stmt.setString(3, user.getBirthdate());
        stmt.setString(4, user.getAddress());
        stmt.setString(5, user.getEmail());
        stmt.setString(6, user.getPhone());
        stmt.setString(7, user.getJob());
        stmt.setString(8, user.getIdCard());
        stmt.setInt(9, user.getId());
        stmt.executeUpdate();
    }

    public void changePassword(int id, String newPassword) throws Exception {
        String sql = "UPDATE user SET password = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, String.valueOf(id));
            ps.executeUpdate();
        }
    }
}