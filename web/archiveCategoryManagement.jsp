<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>档案分类管理</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/myAppinontment.css" rel="stylesheet">
    <script src="js/jquery-3.7.1.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/archiveCategoryManagement.js"></script>
</head>
<body>
<div class="container-fluid">
    <h2>档案分类管理</h2>
    <hr>
    <button class="btn btn-primary" style="margin-bottom: 10px" data-toggle="modal" data-bs-target="#addModal"
            onclick="showadd(this),removeModalBackdrop()">
        新增档案类别
    </button>
    <table class="table table-striped table-hover">
        <thead>
        <tr style="text-align: center">
            <th>ID</th>
            <th>类型名称</th>
            <th>类型编号</th>
            <th>创建日期</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="archiveType" items="${archiveTypes}">
            <tr>
                <td class="text-center">${archiveType.id}</td>
                <td class="text-center">${archiveType.typeName}</td>
                <td class="text-center">${archiveType.typeId}</td>
                <td class="text-center">${archiveType.createDate}</td>
                <td class="text-center">${archiveType.notes}</td>
                <td class="text-center">
                    <button type="button" class="btn btn-outline-success" data-toggle="modal"
                            data-bs-target="#editModal" data-id="${archiveType.id}"
                            data-typename="${archiveType.typeName}"
                            data-typeid="${archiveType.typeId}"
                            data-notes="${archiveType.notes}" onclick="fillModal(this),removeModalBackdrop()">修改
                    </button>
                    <button type="button" class="btn btn-outline-danger"
                            onclick="deleteArchiveCategory(${archiveType.id})">删除
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- 修改分类模态框 -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">修改档案类型</h5>
                </div>
                <div class="modal-body">
                    <form action="UpdateArchiveCategoryServlet">
                        <div class="mb-3">
                            <label for="id" class="form-label">id</label>
                            <input type="text" class="form-control" id="id" name="id" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="typeName" class="form-label">类型名称</label>
                            <input type="text" class="form-control" id="typeName" name="typeName">
                        </div>
                        <div class="mb-3">
                            <label for="typeId" class="form-label">类型编号</label>
                            <input type="text" class="form-control" id="typeId" name="typeId">
                        </div>
                        <div class="mb-3">
                            <label for="notes" class="form-label">备注</label>
                            <textarea class="form-control" id="notes" rows="3" name="notes"></textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                            <input type="submit" class="btn btn-primary" value="保存修改"></div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%--    新增分类模态框--%>
    <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">

                </div>
                <div class="modal-body">
                    <form action="AddArchiveCategoryServlet">
                        <div class="mb-3">
                            <label for="add_typeName" class="form-label">类型名称</label>
                            <input type="text" class="form-control" id="add_typeName" name="add_typeName">
                        </div>
                        <div class="mb-3">
                            <label for="add_typeId" class="form-label">类型编号</label>
                            <input type="text" class="form-control" id="add_typeId" name="add_typeId">
                        </div>
                        <div class="mb-3">
                            <label for="add_notes" class="form-label">备注</label>
                            <textarea class="form-control" id="add_notes" rows="3" name="add_notes"></textarea>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-primary" value="保存">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
