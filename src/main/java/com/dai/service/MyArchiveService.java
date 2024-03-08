package com.dai.service;

import com.dai.model.Archive;
import com.dai.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MyArchiveService {

    public List<Archive> getAllArchives(String owner) throws Exception {
        List<Archive> archives = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM archive where owner=? and filing = 0";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, owner);  // 设置owner的值
            rs = stmt.executeQuery();

            while (rs.next()) {
                Archive archive = new Archive();
                archive.setId(rs.getInt("id"));
                archive.setName(rs.getString("name"));
                archive.setYear(rs.getString("year"));
                archive.setOwner(rs.getString("owner"));
                archive.setType(rs.getString("type"));
                archive.setFilePath(rs.getString("file_path"));
                archive.setCreateDate(rs.getString("create_date"));
                archive.setNotes(rs.getString("notes"));
                archives.add(archive);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return archives;
    }

    public void updateArchive(Archive archive) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE archive SET name = ?, year = ?, notes = ? WHERE owner = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, archive.getName());
            stmt.setString(2, archive.getYear());
            stmt.setString(3, archive.getNotes());
            stmt.setString(4, archive.getOwner());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

}
