package com.dai.service;

import com.dai.model.Archive;
import com.dai.util.DBUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArchiveService {
    private Connection conn;

    public ArchiveService(Connection conn) {
        this.conn = conn;
    }

    public void addArchive(Archive archive) throws Exception {
        String sql = "INSERT INTO archive (name, year, owner,idcard, file_path, notes, type,create_date) VALUES (?, ?, ?, ?,?, ?, ?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, archive.getName());
            stmt.setString(2, archive.getYear());
            stmt.setString(3, archive.getOwner());
            stmt.setString(4, archive.getIdcard());
            stmt.setString(5, archive.getFilePath());
            stmt.setString(6, archive.getNotes());
            stmt.setString(7, archive.getType());
            stmt.setString(8, archive.getCreateDate());
            stmt.executeUpdate();
        }
    }

    public List<Archive> getArchives(int filing) throws Exception {
        List<Archive> archives = new ArrayList<>();
        String sql = "SELECT * FROM archive where filing =" + filing;
        System.out.println(filing);
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Archive archive = new Archive();
                archive.setId(rs.getInt("id"));
                archive.setName(rs.getString("name"));
                archive.setYear(rs.getString("year"));
                archive.setOwner(rs.getString("owner"));
                archive.setIdcard(rs.getString("idcard"));
                archive.setFilePath(rs.getString("file_path"));
                archive.setType(rs.getString("type"));
                archive.setCreateDate(rs.getString("create_date"));
                archive.setNotes(rs.getString("notes"));
                archives.add(archive);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);

        }
        return archives;
    }

    public void deleteArchive(int id, HttpServletRequest req) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            // 先查询出文件路径
            String querySql = "SELECT file_path FROM archive WHERE id = ?";
            PreparedStatement queryStmt = conn.prepareStatement(querySql);
            queryStmt.setInt(1, id);
            ResultSet rs = queryStmt.executeQuery();
            if (rs.next()) {
                String file = rs.getString("file_path");
                String realPath = req.getServletContext().getRealPath("/");
                String filePath = realPath + "file/" + file;
                if (filePath != null && !filePath.isEmpty()) {
                    // 如果文件路径不为空，则删除文件
                    File fileToDelete = new File(filePath);
                    if (fileToDelete.exists() && fileToDelete.isFile()) {
                        if (fileToDelete.delete()) {
                            System.out.println("delete success");
                        } else {
                            System.out.println("delete fail");
                        }
                    } else {
                        System.out.println("no file exist");
                    }
                }
            }

            // 删除数据库记录
            String deleteSql = "DELETE FROM archive WHERE id = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();
        }
    }


    public void filingArchive(int id) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            String filingsql = "UPDATE archive SET filing= 1 WHERE id=?";
            PreparedStatement filingstmt = conn.prepareStatement(filingsql);
            filingstmt.setInt(1, id);
            filingstmt.executeUpdate();
        }
    }

    public void restoreArchive(int id) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            String restoresql = "UPDATE archive SET filing= 0 WHERE id=?";
            PreparedStatement restorestmt = conn.prepareStatement(restoresql);
            restorestmt.setInt(1, id);
            restorestmt.executeUpdate();
        }
    }
}
