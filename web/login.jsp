<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        body {
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
            background-image: url('img/640.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
    </style>
    <script>
        window.onload = function () {
            var error = '<%= request.getSession().getAttribute("error") %>';
            if (error && error != 'null') {
                alert(error);
                <% request.getSession().removeAttribute("error"); %>
            }
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row justify-content-center align-items-center vh-100 ">
        <div class="col-5 shadow-lg p-3 mb-5 bg-white rounded">
            <div class="card border-0">
                <div class="card-body">
                    <h2 class="text-center mb-4">欢迎登录档案管理系统</h2>
                    <form action="LoginServlet" method="post">
                        <div class="form-group" style="padding: 10px">
                            <label for="username">用户名:</label>
                            <input type="text" id="username" name="username" class="form-control">
                        </div>
                        <div class="form-group" style="padding: 10px">
                            <label for="password">密码:</label>
                            <input type="password" id="password" name="password" class="form-control">
                        </div>
                        <div class="form-group text-center" style="padding: 10px">
                            <input type="submit" value="登录" class="btn btn-block btn-primary btn-lg"
                                   style="width: 100%">
                        </div>
                    </form>
                    <div class="form-group text-center" style="padding: 10px">
                        <a href="register.jsp" class="btn btn-block btn-outline-success btn-lg"
                           style="width: 100%">注册</a>
                    </div>
                    <a href="admin_login.jsp" class="d-block text-center" style="padding: 10px">管理员登录</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
