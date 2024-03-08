package com.dai.service;

import com.dai.model.User;
import com.dai.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManagementPersonnelService {
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM user";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setReal_name(rs.getString("real_name"));
                user.setUsername(rs.getString("username"));
                user.setIdCard(rs.getString("idcard"));
                user.setBirthdate(rs.getString("birthdate"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setJob(rs.getString("job"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void updatePerson(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "UPDATE user SET real_name = ?, username = ?, birthdate = ?, address = ?, email = ?, phone = ?, job = ? ,idcard = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getReal_name());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getBirthdate());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getJob());
            ps.setString(8, user.getIdCard());
            ps.setInt(9, user.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id, String realName) {
        try (Connection conn = DBUtil.getConnection()) {
            // 再删除user表中的记录
            String sqlUser = "DELETE FROM user WHERE id = ?";
            PreparedStatement psUser = conn.prepareStatement(sqlUser);
            psUser.setInt(1, id);
            psUser.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
