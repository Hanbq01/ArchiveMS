<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dai.model.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <!-- 引入 Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<h1><%= user.getReal_name() %> 欢迎您使用档案管理系统</h1>
</body>
</html>
