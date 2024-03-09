package com.dai.service;

import com.dai.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartService {
    public List<Map<String, Object>> getTypeDistribution() {
        return getDistribution("SELECT type, COUNT(*) AS count FROM archive GROUP BY type");
    }

    public List<Map<String, Object>> getBirthDistribution() {
        return getDistribution("SELECT YEAR(birthdate) AS year, COUNT(*) AS count FROM user GROUP BY year");
    }

    public List<Map<String, Object>> getAddressDistribution() {
        List<Map<String, Object>> data = new ArrayList<>();

        // 中国的所有省份
        String[] provinces = {"河北", "山西", "辽宁", "吉林", "黑龙江", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "海南", "四川", "贵州", "云南", "陕西", "甘肃", "青海", "台湾", "内蒙古", "广西", "西藏", "宁夏", "新疆", "北京", "天津", "上海", "重庆", "香港", "澳门"};

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE address LIKE ?")) {

            for (String province : provinces) {
                stmt.setString(1, "%" + province + "%");

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        if (count > 0) {
                            Map<String, Object> item = new HashMap<>();
                            item.put("name", province);
                            item.put("value", count);
                            data.add(item);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


    private List<Map<String, Object>> getDistribution(String sql) {
        List<Map<String, Object>> data = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("name", rs.getString(1));
                item.put("value", rs.getInt(2));
                data.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}