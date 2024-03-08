<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>在线申请</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/application.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div id="successMessage" class="alert alert-success alert-dismissible fade show" role="alert" style="display: none;">
        <strong>成功!</strong> 你的信息已保存.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <div id="errorMessage" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;">
        <strong>错误!</strong> 请稍后再试，或与管理员联系！
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <form id="applicantForm" action="ApplicantServlet" method="post">
        <h2>办理人信息</h2>
        <hr>
        <div class="input-row">
            <label for="name">姓&emsp;&emsp;名:</label>
            <input type="text" id="name" name="name" class="form-control" required>
            <span>此项为必填项。</span>
        </div>
        <div class="input-row">
            <label for="id_card">身份证号:</label>
            <input type="text" id="id_card" name="id_card" class="form-control" required>
            <span>此项为必填项,需正确输入18位身份证号。</span>
        </div>
        <div class="input-row">
            <label for="phone">联系方式:</label>
            <input type="text" id="phone" name="phone" class="form-control" required>
            <span>此项为必填项，请填写正确的手机号或者固定电话号码。</span>
        </div>
        <div class="input-row">
            <label for="native_place">籍&emsp;&emsp;贯:</label>
            <input type="text" id="native_place" name="native_place" class="form-control" required>
            <span>此项为必填项，请按格式正确填写，例如：山东德州。</span>
        </div>

        <h2>业务信息</h2>
        <hr>
        <div class="input-row">
            <label for="query_purpose">查&ensp;询&ensp;目&ensp;的&ensp;:</label>
            <input type="text" id="query_purpose" name="query_purpose" class="form-control" required>
            <span>请如实填写原因。</span>
        </div>
        <div class="input-row">
            <label for="queried_name">被查询人姓名:</label>
            <input type="text" id="queried_name" name="queried_name" class="form-control" required>
            <span>此项为必填项。</span>
        </div>
        <div class="input-row">
            <label for="queried_profession">被查询人专业:</label>
            <input type="text" id="queried_profession" name="queried_profession" class="form-control" required>
            <span>此项为必填项。</span>
        </div>


        <h2>上传凭证</h2>
        <hr>
        <div class="input-row">
            <label for="image">选&ensp;择&ensp;图&ensp;片&ensp;:</label>
            <input type="file" id="image" name="image" class="form-control" accept="image/*" required>
            <span>此项为必填项，请上传.jpg或.png格式的图片。</span>
        </div>

        <h2>其他信息</h2>
        <hr>
        <div class="input-row">
            <label for="notes">备&emsp;&emsp;注:</label>
            <textarea id="notes" name="notes" class="form-control" rows="4" cols="50"></textarea>
            <span></span>
        </div>


        <input type="submit" value="保存" class="btn btn-primary" style="width: 50%; display: block; margin: 20px auto;">
    </form>

</div>

<script src="js/jquery-3.7.1.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/Applicantion.js"></script>
</body>
</html>
