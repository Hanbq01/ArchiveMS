<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的预约</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/myAppinontment.css" rel="stylesheet">
    <script src="js/jquery-3.7.1.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/myAppiontment.js"></script>
</head>
<body>
<div class="container-fluid">
    <h2>我的预约信息</h2>
    <hr>

    <table class="table table-striped table-hover">
        <thead>
        <tr style="text-align: center">
            <th>序号</th>
            <th>姓名</th>
            <th>身份证号</th>
            <th>籍贯</th>
            <th>查询目的</th>
            <th>被查询人姓名</th>
            <th>被查询人专业</th>
            <th>预约时间</th>
            <th>预约方式</th>
            <th>审核状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="applicant" items="${applicants}">
            <tr>
                <td class="text-center">${applicant.id}</td>
                <td class="text-center">${applicant.name}</td>
                <td class="text-center">${applicant.idCard}</td>
                <td class="text-center">${applicant.nativePlace}</td>
                <td class="text-center">${applicant.queryPurpose}</td>
                <td class="text-center">${applicant.queriedName}</td>
                <td class="text-center">${applicant.queriedProfession}</td>
                <td class="text-center">
                    <c:choose>
                        <c:when test="${empty applicant.appointment_date}">
                            ——
                        </c:when>
                        <c:otherwise>
                            ${applicant.appointment_date}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="text-center">
                    <c:choose>
                        <c:when test="${applicant.role == 1}">
                            到馆预约
                        </c:when>
                        <c:otherwise>
                            在线申请
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="text-center">
                    <c:choose>
                        <c:when test="${applicant.review == 1}">
                            <span style="color: green">审核通过</span>
                        </c:when>
                        <c:when test="${applicant.review == 2}">
                            <span style="color: red">审核不通过</span>
                        </c:when>
                        <c:otherwise>
                            <span style="color: blue">等待审核</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="text-center">
                    <button type="button" class="btn btn-outline-danger" onclick="cancelAppointment(${applicant.id})">
                        取消预约
                    </button>
                    <button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal"
                            data-bs-target="#myModal" data-id="${applicant.id}" data-name="${applicant.name}"
                            data-idCard="${applicant.idCard}" data-nativePlace="${applicant.nativePlace}"
                            data-queryPurpose="${applicant.queryPurpose}" data-queriedName="${applicant.queriedName}"
                            data-queriedProfession="${applicant.queriedProfession}"
                            data-appointment_date="${applicant.appointment_date}"
                            data-image_path="${applicant.imagePath}"
                            data-role="${applicant.role}" data-notes="${applicant.notes}"
                            data-review_note="${applicant.reviewNote}"
                            onclick="fillModal(this),removeModalBackdrop()">详细信息
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="id">序号</label>
                        <input type="text" class="form-control" id="id" readonly>
                    </div>
                    <div class="form-group">
                        <label for="name">姓名</label>
                        <input type="text" class="form-control" id="name" readonly>
                    </div>
                    <div class="form-group">
                        <label for="idCard">身份证号</label>
                        <input type="text" class="form-control" id="idCard" readonly>
                    </div>
                    <div class="form-group">
                        <label for="nativePlace">籍贯</label>
                        <input type="text" class="form-control" id="nativePlace" readonly>
                    </div>
                    <div class="form-group">
                        <label for="queryPurpose">查询目的</label>
                        <input type="text" class="form-control" id="queryPurpose" readonly>
                    </div>
                    <div class="form-group">
                        <label for="queriedName">被查询人姓名</label>
                        <input type="text" class="form-control" id="queriedName" readonly>
                    </div>
                    <div class="form-group">
                        <label for="queriedProfession">被查询人专业</label>
                        <input type="text" class="form-control" id="queriedProfession" readonly>
                    </div>
                    <div class="form-group">
                        <label for="appointment_date">预约时间</label>
                        <input type="text" class="form-control" id="appointment_date" readonly>
                    </div>
                    <div class="form-group">
                        <label for="imagePath">凭证图片</label>
                        <img id="imagePath" src="" alt="Image" style="width:100%;max-width:300px">
                        <p>到馆预约无需凭证</p>
                    </div>
                    <div class="form-group">
                        <label for="role">预约方式</label>
                        <input type="text" class="form-control" id="role" readonly>
                    </div>
                    <div class="form-group">
                        <label for="notes">备注</label>
                        <textarea class="form-control" id="notes" rows="3" readonly></textarea>
                    </div>
                    <div class="form-group">
                        <label for="review_note">审核意见</label>
                        <input type="text" class="form-control" id="review_note" readonly>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-bs-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
