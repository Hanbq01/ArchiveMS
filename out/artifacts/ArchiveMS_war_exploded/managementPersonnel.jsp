<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理人员</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/myAppinontment.css" rel="stylesheet">
    <script src="js/jquery-3.7.1.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/managementPersonnel.js"></script>
</head>
<body>
<div class="container-fluid">
    <h2>管理人员</h2>
    <hr>
    <form action="UserSearchServlet" method="get" class="row g-3 align-items-center" style="margin-bottom: 10px">
        <div class="col-auto">
            <label for="real_name" class="col-form-label">姓名</label>
        </div>
        <div class="col-auto">
            <input type="text" class="form-control" id="real_name" name="real_name" placeholder="输入姓名">
        </div>
        <div class="col-auto">
            <label for="idCard" class="col-form-label">身份证号</label>
        </div>
        <div class="col-auto">
            <input type="text" class="form-control" id="idCard" name="idCard" placeholder="输入身份证号">
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-success">搜索</button>
        </div>
    </form>


    <table class="table table-striped table-hover">
        <thead>
        <tr style="text-align: center">
            <th>序号</th>
            <th>姓名</th>
            <th>身份证号</th>
            <th>用户名</th>
            <th>出生日期</th>
            <th>地址</th>
            <th>电子邮件</th>
            <th>电话</th>
            <th>职位</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td class="text-center">${user.id}</td>
                <td class="text-center">${user.real_name}</td>
                <td class="text-center">${user.idCard}</td>
                <td class="text-center">${user.username}</td>
                <td class="text-center">${user.birthdate}</td>
                <td class="text-center">${user.address}</td>
                <td class="text-center">${user.email}</td>
                <td class="text-center">${user.phone}</td>
                <td class="text-center">${user.job}</td>
                <td class="text-center">
                    <button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal"
                            data-bs-target="#userDetailModal" data-id="${user.id}" data-realName="${user.real_name}"
                            data-idCard="${user.idCard}" data-username="${user.username}"
                            data-birthdate="${user.birthdate}" data-address="${user.address}"
                            data-email="${user.email}" data-phone="${user.phone}" data-job="${user.job}"
                            onclick="fillModal(this),removeModalBackdrop()">
                        详细信息
                    </button>
                    <button type="button" class="btn btn-outline-danger" onclick="deleteUser(${user.id})">删除</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- 详细信息 -->
<div class="modal fade" id="userDetailModal" tabindex="-1" aria-labelledby="userDetailModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="userDetailModalLabel">详细信息</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="ManagementPersonnelUpdateServlet">
                    <div class="mb-3" style="display: none">
                        <label for="id" class="form-label">序号</label>
                        <input type="text" class="form-control" id="id" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="realName" class="form-label">姓名</label>
                        <input type="text" class="form-control" id="realName">
                    </div>
                    <div class="mb-3">
                        <label for="idCard" class="form-label">身份证号</label>
                        <input type="text" class="form-control" id="idCard">
                    </div>
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input type="text" class="form-control" id="username">
                    </div>
                    <div class="mb-3">
                        <label for="birthdate" class="form-label">出生日期</label>
                        <input type="date" class="form-control" id="birthdate">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">地址</label>
                        <input type="text" class="form-control" id="address">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">电子邮件</label>
                        <input type="email" class="form-control" id="email">
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">电话</label>
                        <input type="tel" class="form-control" id="phone">
                    </div>
                    <div class="mb-3">
                        <label for="job" class="form-label">职位</label>
                        <input type="text" class="form-control" id="job">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">保存更改</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
