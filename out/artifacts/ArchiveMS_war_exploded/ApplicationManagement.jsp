<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>我的预约</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/myAppinontment.css" rel="stylesheet">
    <script src="js/jquery-3.7.1.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script>
        function fillModal(button) {
            document.getElementById('id').value = button.getAttribute('data-id');
            document.getElementById('name').value = button.getAttribute('data-name');
            document.getElementById('idCard').value = button.getAttribute('data-idCard');
            document.getElementById('nativePlace').value = button.getAttribute('data-nativePlace');
            document.getElementById('queryPurpose').value = button.getAttribute('data-queryPurpose');
            document.getElementById('queriedName').value = button.getAttribute('data-queriedName');
            document.getElementById('queriedProfession').value = button.getAttribute('data-queriedProfession');
            var imagePath = button.getAttribute('data-image_path');
            var img = document.getElementById('imagePath');
            var p = img.nextElementSibling; // 假设 <p> 元素紧跟在 <img> 元素后面
            if (imagePath) {
                img.src = "img/pic/" + imagePath;
                img.style.display = ""; // 显示 img 元素
                p.style.display = "none"; // 隐藏 p 元素
            } else {
                img.style.display = "none"; // 隐藏 img 元素
                p.style.display = ""; // 显示 p 元素
            }
            document.getElementById('appointment_date').value = button.getAttribute('data-appointment_date');
            document.getElementById('role').value = button.getAttribute('data-role') == 1 ? '到馆预约' : '在线申请';
            document.getElementById('notes').value = button.getAttribute('data-notes');

            $('#myModal').on('hidden.bs.modal', function (e) {
                $('.modal-backdrop').remove();
            });
        }


        //移除模态框背景色
        function removeModalBackdrop() {
            $('.modal-backdrop').remove();
        }


        function reviewApplication(id) {
            document.getElementById('reviewId').value = id; // 将 id 存储在隐藏的 input 元素中
            var reviewModal = new bootstrap.Modal(document.getElementById('reviewModal'));
            reviewModal.show(); // 显示模态框
        }


        function submitReview() {
            var id = document.getElementById('reviewId').value;
            var reviewOption = document.querySelector('input[name="reviewOption"]:checked').value;
            var reviewNote = document.getElementById('reviewNote').value;
            var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
            var button = document.querySelector(`button[data-id='${id}']`); // 获取当前按钮
            $.ajax({
                url: contextPath + '/ReviewServlet',
                type: 'POST',
                data: {'id': id, 'action': reviewOption, 'reviewNote': reviewNote},
                success: function (response) {
                    $('#content').load('ApplicationManagementServlet'); // 刷新页面
                    // 显示 Bootstrap 5 alert 提示框
                    var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>审核成功！</div>');
                    $('body').append(alertDiv);
                    setTimeout(function () {
                        $(alertDiv).fadeOut('slow');
                    }, 3000);  // 3秒后自动消失
                },
                error: function (error) {
                    console.error(error);
                }
            });
        }

    </script>
</head>
<body>
<div class="container-fluid">
    <h2>我的预约信息</h2>
    <hr>
    <p style="color: red">预约信息15天后自动清除，请尽快审核</p>
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
                <td style="display: none" id="reviewcheck">${applicant.review}</td>
                <td class="text-center">
                    <button type="button" class="btn btn-secondary" data-bs-toggle="modal"
                            data-bs-target="#myModal" data-id="${applicant.id}" data-name="${applicant.name}"
                            data-idCard="${applicant.idCard}" data-nativePlace="${applicant.nativePlace}"
                            data-queryPurpose="${applicant.queryPurpose}" data-queriedName="${applicant.queriedName}"
                            data-queriedProfession="${applicant.queriedProfession}"
                            data-appointment_date="${applicant.appointment_date}"
                            data-image_path="${applicant.imagePath}"
                            data-role="${applicant.role}" data-notes="${applicant.notes}"
                            onclick="fillModal(this),removeModalBackdrop()">详细信息
                    </button>

                    <c:choose>
                        <c:when test="${applicant.review == 0}">
                            <button type="button" class="btn btn-primary" id="reviewbutton"
                                    onclick="reviewApplication(${applicant.id}),removeModalBackdrop()">
                                审核
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-success" id="reviewed" disabled>
                                已审核
                            </button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--详细信息模态框--%>
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
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-bs-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<%--审核--%>
<div class="modal fade" id="reviewModal" tabindex="-1" aria-labelledby="reviewModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="reviewModalLabel">审核</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="reviewForm">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="reviewOption" id="approveOption"
                               value="approve" checked>
                        <label class="form-check-label" for="approveOption">
                            审核通过
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="reviewOption" id="rejectOption"
                               value="reject">
                        <label class="form-check-label" for="rejectOption">
                            审核不通过
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="reviewNote">审核意见</label>
                        <textarea class="form-control" id="reviewNote" rows="3"></textarea>
                    </div>
                    <input type="hidden" id="reviewId">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="submitReview()">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
