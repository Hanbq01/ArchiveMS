<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.sql.*" %>
<%@ page import="com.dai.util.DBUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    List<String> types = new ArrayList<>();
    try {
        Connection conn = DBUtil.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT type_name FROM archive_type";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            types.add(rs.getString("type_name"));
        }
        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>


<html>
<head>
    <title>新增档案</title>
    <!-- 引入 Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/archiveadd.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <form action="AddArchiveServlet" id="addArchiveForm" method="post" enctype="multipart/form-data">
        <h2>新增档案</h2>
        <hr>
        <div class="input-row">
            <label for="name">档案名:&emsp;</label>
            <input type="text" id="name" name="name" class="form-control" required>
            <span>此项为必填项。</span>
        </div>
        <div class="input-row">
            <label for="year">所属年份:</label>
            <select id="year" name="year" class="form-control" required>
                <option value="">请选择年份</option>
                <c:forEach var="i" begin="2000" end="2024">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
            <span>此项为必填项。</span>
        </div>
        <div class="input-row">
            <label for="owner">拥有者:&emsp;</label>
            <input type="owner" id="owner" name="owner" class="form-control" required>
            <span>此项为必填项。</span>
        </div>
        <div class="input-row">
            <label for="owner">身份证号:</label>
            <input type="owner" id="idcard" name="idcard" class="form-control" required>
            <span>此项为必填项。且必须填写18位身份证号</span>
        </div>
        <div class="input-row">
            <label for="type">类&emsp;&emsp;型:</label>
            <select id="type" name="type" class="form-control" required>
                <option value="">请选择类型</option>
                <% for (String type : types) { %>
                <option value="<%= type %>"><%= type %>
                </option>
                <% } %>
            </select>
            <span>此项为必填项。</span>
        </div>
        <div class="input-row">
            <label for="file_path">档案文件:</label>
            <input type="file" id="file_path" name="file_path" class="form-control" accept=".doc,.docx,.pdf" required>
            <span>此项为必填项。</span>
        </div>
        <div class="input-row">
            <label for="notes">备&emsp;&emsp;注:</label>
            <textarea id="notes" name="notes" class="form-control" rows="4" cols="50"></textarea>
            <span></span>
        </div>

        <button type="submit" class="btn btn-primary" style="width: 50%; display: block; margin: 20px auto;">提交
        </button>
    </form>
</div>

<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery-3.7.1.js"></script>
<script src="js/addArchive.js"></script>
</body>
</html>
