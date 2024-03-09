<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>在线注册</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            width: 100%;
            margin: auto;
        }

        h2 {
            margin-top: 20px;
            text-align: left;
        }

        hr {
            border: 1px solid #000;
        }

        form {
            width: 75%;
            margin: auto;
            display: grid;
            gap: 10px;
            align-items: center;
        }

        .input-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
        }

        .input-row input {
            width: 50%;
        }

        .input-row span {
            width: 25%;
            font-size: 0.8em;
            color: gray;
            text-align: right;
        }

        .alert {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div id="successMessage" class="alert alert-success alert-dismissible fade show" role="alert"
         style="display: none;">
        <strong>成功!</strong> 你的信息已保存.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <div id="errorMessage" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;">
        <strong>错误!</strong> 请稍后再试，或与管理员联系！
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <form id="registerForm" action="RegisterServlet" method="post">
        <h2>注册</h2>
        <hr>
        <div class="input-row">
            <label for="real_name">真实姓名:</label>
            <input type="text" id="real_name" name="real_name" class="form-control" required>
            <span>此项为必填项。</span>
        </div>
        <div class="input-row">
            <label for="username">用户名&emsp;:</label>
            <input type="text" id="username" name="username" class="form-control" required>
            <span>此项为必填项。</span>
        </div>
        <div class="input-row">
            <label for="password">密&emsp;&emsp;码:</label>
            <input type="password" id="password" name="password" class="form-control" required>
            <span>此项为必填项。</span>
        </div>

        <div class="input-row">
            <label for="confimpwd">确认密码:</label>
            <input type="password" id="confimpwd" name="confimpwd" class="form-control" required>
            <span>此项为必填项。</span>
        </div>
        <div class="input-row">
            <label for="idCard">身份证号:</label>
            <input type="date" id="idCard" name="idCard" class="form-control" required>
            <span>此项为必填项。</span>
        </div>
        <div class="input-row">
            <label for="birthdate">出生日期:</label>
            <input type="date" id="birthdate" name="birthdate" class="form-control">
            <span>此项可以为空。</span>
        </div>
        <div class="input-row">
            <label for="address">地&emsp;&emsp;址:</label>
            <input type="text" id="address" name="address" class="form-control">
            <span>此项可以为空。</span>
        </div>
        <div class="input-row">
            <label for="email">电子邮箱:</label>
            <input type="text" id="email" name="email" class="form-control">
            <span>此项可以为空。</span>
        </div>
        <div class="input-row">
            <label for="phone">电话号码:</label>
            <input type="text" id="phone" name="phone" class="form-control">
            <span>此项可以为空。</span>
        </div>
        <div class="input-row">
            <label for="job">职&emsp;&emsp;业:</label>
            <input type="text" id="job" name="job" class="form-control">
            <span>此项可以为空。</span>
        </div>

        <input type="submit" value="注册" class="btn btn-primary"
               style="width: 50%; display: block; margin: 20px auto;">
    </form>
</div>
<script src="js/jquery-3.7.1.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/register.js"></script>
</body>
</html>