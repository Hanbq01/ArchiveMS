package com.dai.service;

import com.dai.model.User;

import java.sql.*;

public class RegisterService {
    private Connection conn;

    public RegisterService(Connection conn) {
        this.conn = conn;
    }

    public void saveUser(User user) throws SQLException {
        String sql = "INSERT INTO user (real_name, username, password, role, birthdate, address, email, phone, job,idcard) VALUES (?, ?, ?, 1, ?, ?, ?,?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getReal_name());
        stmt.setString(2, user.getUsername());
        stmt.setString(3, user.getPassword());
        stmt.setString(4, user.getBirthdate());
        stmt.setString(5, user.getAddress());
        stmt.setString(6, user.getEmail());
        stmt.setString(7, user.getPhone());
        stmt.setString(8, user.getJob());
        stmt.setString(9, user.getIdCard());
        stmt.executeUpdate();
    }
}