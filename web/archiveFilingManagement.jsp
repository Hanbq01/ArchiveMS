<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>档案管理</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/myAppinontment.css" rel="stylesheet">
    <script src="js/jquery-3.7.1.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/archiveCategoryManagement.js"></script>
</head>
<body>
<div class="container-fluid">
    <h2>归档档案管理</h2>
    <hr>
    <table class="table table-striped table-hover">
        <thead>
        <tr style="text-align: center">
            <th>档案编号</th>
            <th>名称</th>
            <th>年份</th>
            <th>所有者</th>
            <th>身份证号</th>
            <th>文件路径</th>
            <th>创建时间</th>
            <th>类型</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="archive" items="${archives}">
            <tr>
                <td class="text-center">${archive.id}</td>
                <td class="text-center">${archive.name}</td>
                <td class="text-center">${archive.year}</td>
                <td class="text-center">${archive.owner}</td>
                <td class="text-center">${archive.idcard}</td>
                <td class="text-center"><a href="file/${archive.filePath}" download>${archive.filePath}</a></td>
                <td class="text-center">${archive.createDate}</td>
                <td class="text-center">${archive.type}</td>
                <td class="text-center">${archive.notes}</td>
                <td class="text-center">
                    <button type="button" class="btn btn-outline-primary" onclick="restoreArchive(${archive.id})">还原
                    </button>
                    <button type="button" class="btn btn-outline-danger"
                            onclick="deleteArchive(${archive.id})">删除
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
