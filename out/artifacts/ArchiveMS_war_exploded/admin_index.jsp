<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <!-- 引入 Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- 自定义 CSS -->
    <style>
        .navbar-custom, .navbar-custom .nav-link {
            background-color: #708090;
            color: #FFFFFF;
            font-size: 18px;
        }

        .navbar-custom .nav-link:hover {
            color: #000000;
        }

        .dropdown-menu .dropdown-item {
            text-align: center;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-custom">
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav me-auto">
            <li class="nav-item">
                <a class="nav-link" href="#" id="home">首页</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="archiveManagementMenu" role="button"
                   data-bs-toggle="dropdown">
                    档案管理
                </a>
                <ul class="dropdown-menu" aria-labelledby="archiveManagement">
                    <li><a class="dropdown-item" href="#" id="addArchive">新增档案</a></li>
                    <li><a class="dropdown-item" href="#" id="archiveCategoryManagement">档案分类管理</a></li>
                    <li><a class="dropdown-item" href="#" id="archiveManagement">档案管理</a></li>
                    <li><a class="dropdown-item" href="#" id="archiveFilingManagement">归档档案管理</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" id="ApplicationManagement">
                    档案预约管理
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" id="managementPersonnel">管理人员</a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-bs-toggle="dropdown">
                    个人账户
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="login.jsp" style="text-align: left">退出登录</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>

<div id="content">
    <!-- 这里将根据点击的不同选项卡进行刷新 -->
</div>

<script src="js/jquery-3.7.1.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/admin_index.js"></script>

</body>
</html>
