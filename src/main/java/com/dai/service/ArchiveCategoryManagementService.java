package com.dai.service;

import com.dai.model.ArchiveType;
import com.dai.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArchiveCategoryManagementService {
    public List<ArchiveType> getAllArchiveTypes() {
        List<ArchiveType> archiveTypes = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM archive_type";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ArchiveType archiveType = new ArchiveType();
                archiveType.setId(rs.getInt("id"));
                archiveType.setTypeName(rs.getString("type_name"));
                archiveType.setTypeId(rs.getString("type_id"));
                archiveType.setCreateDate(rs.getTimestamp("create_date").toString());
                archiveType.setNotes(rs.getString("notes"));
                archiveTypes.add(archiveType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return archiveTypes;
    }

    public void updateArchiveCategory(ArchiveType archiveType) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "UPDATE archive_type SET type_id = ?, type_name = ?,create_date = ?, notes = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, archiveType.getTypeId());
            stmt.setString(2, archiveType.getTypeName());
            stmt.setString(3, archiveType.getCreateDate());
            stmt.setString(4, archiveType.getNotes());
            stmt.setInt(5, archiveType.getId());
            stmt.executeUpdate();
        }
    }

    public void addArchiveCategory(ArchiveType archiveType) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "insert into archive_type (type_id,type_name,create_date,notes) values (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, archiveType.getTypeId());
            stmt.setString(2, archiveType.getTypeName());
            stmt.setString(3, archiveType.getCreateDate());
            stmt.setString(4, archiveType.getNotes());
            stmt.executeUpdate();
        }
    }

    public void deleteArchiveCategory(int id) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "delete from archive_type where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, String.valueOf(id));
            stmt.executeUpdate();
        }
    }
}