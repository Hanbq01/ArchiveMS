<%@ page import="java.util.List" %>
<%@ page import="com.dai.model.Archive" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的档案</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/myArchive.css" rel="stylesheet">
    <script src="js/myArchive.js"></script>
</head>
<body>
<div class="container-fluid">
    <h2>我的档案</h2>
    <hr>
    <table class="table table-striped table-hover">
        <thead>
        <tr style="text-align: center">
            <th>序号</th>
            <th>档案名</th>
            <th>所属年份</th>
            <th>拥有者</th>
            <th>档案类型</th>
            <th>档案文件</th>
            <th>创建时间</th>
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
                <td class="text-center">${archive.type}</td>
                <td class="text-center"><a href="file/${archive.filePath}" download>${archive.filePath}</a></td>
                <td class="text-center">${archive.createDate}</td>
                <td class="text-center">${archive.notes}</td>
                <td class="text-center">
                    <button class="btn btn-primary"
                            onclick="openModal('${archive.id}', '${archive.name}', '${archive.year}', '${archive.notes}')">
                        修改
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Edit Modal -->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">修改档案</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="editForm" method="post" action="UpdateArchiveServlet">
                    <input type="hidden" id="archiveId" name="id">
                    <div class="form-group">
                        <label for="archiveName">档案名</label>
                        <input type="text" class="form-control" id="archiveName" name="name">
                    </div>
                    <div class="form-group">
                        <label for="archiveYear">所属年份</label>
                        <select class="form-control" id="archiveYear" name="year">
                            <option value="">请选择年份</option>
                            <% for (int year = 2000; year <= 2024; year++) { %>
                            <option value="<%= year %>"><%= year %>
                            </option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="archiveNotes">备注</label>
                        <input type="text" class="form-control" id="archiveNotes" name="notes">
                    </div>
                    <input type="submit" class="btn btn-primary" value="保存"></input>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
