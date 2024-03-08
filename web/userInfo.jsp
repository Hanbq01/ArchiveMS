<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/userinfo.css" rel="stylesheet">
    <script src="js/userinfo.js"></script>
</head>
<body>
<div class="container-fluid">
    <h2>个人信息</h2>
    <hr>

    <div class="row">
        <div class="col-md-6">
            <div class="input-row">
                <label for="real_name">姓名:</label>
                <span id="real_name">${user.real_name}</span>
            </div>
            <div class="input-row">
                <label for="username">用户名:</label>
                <span id="username">${user.username}</span>
            </div>
            <div class="input-row">
                <label for="idcard">身份证号:</label>
                <span id="idcard">${user.idCard}</span>
            </div>
            <div class="input-row">
                <label for="birthdate">出生日期:</label>
                <span id="birthdate">${user.birthdate}</span>
            </div>
            <div class="input-row">
                <label for="address">地址:</label>
                <span id="address">${user.address}</span>
            </div>
            <div class="input-row">
                <label for="email">电子邮箱:</label>
                <span id="email">${user.email}</span>
            </div>
            <div class="input-row">
                <label for="phone">电话号码:</label>
                <span id="phone">${user.phone}</span>
            </div>
            <div class="input-row">
                <label for="job">职业:</label>
                <span id="job">${user.job}</span>
            </div>
            <div class="input-row">
                <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal"
                        data-bs-target="#editInfoModal" onclick="removeModalBackdrop()">修改信息
                </button>
                <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal"
                        data-bs-target="#changePasswordModal" onclick="removeModalBackdrop()">修改密码
                </button>
            </div>
        </div>
        <div class="col-md-6">
            <div class="input-row">
                <label for="head">头像:</label>
                <div style="display: flex; flex-direction: column; align-items: center;">
                    <img id="userHead" src="${empty user.head_path ? 'img/commonhead.jpg' : user.head_path}"
                         style="width: 200px; height: 200px; border-radius: 50%; object-fit: cover;">
                    <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal"
                            data-bs-target="#uploadModal" style="margin-top: 20px;" onclick="removeModalBackdrop()">修改头像
                    </button>
                </div>
            </div>
        </div>

        <!-- 修改头像模态框 -->
        <div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="uploadModalLabel">上传新的头像</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="UploadServlet" method="post" enctype="multipart/form-data">
                            <input type="file" id="head" name="head" accept="image/*" onchange="checkFile()"/>
                            <p style="font-size: 0.8em; color: #888;">只允许上传jpg和png格式的头像</p>
                            <input type="submit" value="上传头像" class="btn btn-outline-primary" id="submitBtn"
                                   disabled/>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- 修改信息模态框 -->
        <div class="modal fade" id="editInfoModal" tabindex="-1" aria-labelledby="editInfoModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editInfoModalLabel">修改信息</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="editInfoForm" action="UpdateUserInfoServlet" method="post">
                            <div class="mb-3">
                                <label for="modal_real_name" class="form-label">姓名</label>
                                <input type="text" class="form-control" id="modal_real_name" name="real_name"
                                       value="${user.real_name}">
                            </div>
                            <div class="mb-3">
                                <label for="modal_username" class="form-label">用户名</label>
                                <input type="text" class="form-control" id="modal_username" name="username"
                                       value="${user.username}">
                            </div>
                            <div class="mb-3">
                                <label for="modal_idcard" class="form-label">身份证号</label>
                                <input type="text" class="form-control" id="modal_idcard" name="idcard"
                                       value="${user.idCard}">
                            </div>
                            <div class="mb-3">
                                <label for="modal_birthdate" class="form-label">出生日期</label>
                                <input type="date" class="form-control" id="modal_birthdate" name="birthday"
                                       value="${user.birthdate}">
                            </div>
                            <div class="mb-3">
                                <label for="modal_address" class="form-label">地址</label>
                                <input type="text" class="form-control" id="modal_address" name="address"
                                       value="${user.address}">
                            </div>
                            <div class="mb-3">
                                <label for="modal_email" class="form-label">电子邮箱</label>
                                <input type="email" class="form-control" id="modal_email" name="email"
                                       value="${user.email}">
                            </div>
                            <div class="mb-3">
                                <label for="modal_phone" class="form-label">电话号码</label>
                                <input type="tel" class="form-control" id="modal_phone" name="phone"
                                       value="${user.phone}">
                            </div>
                            <div class="mb-3">
                                <label for="modal_job" class="form-label">职业</label>
                                <input type="text" class="form-control" id="modal_job" name="job" value="${user.job}">
                            </div>
                            <div class="mb-3">
                                <button type="submit" class="btn btn-primary">保存</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- 修改密码模态框 -->
        <div class="modal fade" id="changePasswordModal" tabindex="-1" aria-labelledby="changePasswordModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="changePasswordModalLabel">修改密码</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="changePasswordForm" action="ChangePasswordServlet" method="post">
                            <div class="mb-3">
                                <label for="newPassword" class="form-label">新密码</label>
                                <input type="password" class="form-control" id="newPassword" name="newPassword">
                            </div>
                            <div class="mb-3">
                                <label for="confirmNewPassword" class="form-label">确认新密码</label>
                                <input type="password" class="form-control" id="confirmNewPassword"
                                       name="confirmNewPassword">
                                <small id="passwordError" class="form-text text-danger"></small>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                                <input type="submit" class="btn btn-primary" value="保存更改">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>
