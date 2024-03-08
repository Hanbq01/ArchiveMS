//移除模态框背景色
function removeModalBackdrop() {
    $('.modal-backdrop').remove();
}

// 检查文件输入框是否为空
function checkFile() {
    var file = $('#head').val();
    if (file == '') {
        $('#submitBtn').prop('disabled', true);
    } else {
        $('#submitBtn').prop('disabled', false);
    }
}

$(document).ready(function () {
    checkFile();
});
//提交修改头像
$('form[action="UploadServlet"]').submit(function (e) {
    e.preventDefault();
    var formData = new FormData(this);
    $.ajax({
        url: 'UploadServlet',
        type: 'POST',
        data: formData,
        success: function (data) {
            if (data.trim() == "success") {
                $('#content').load('UserInfoServlet', function () {
                    // 更新头像的 src 属性
                    $('#userHead').attr('src', $('#userHead').attr('src') + '?' + new Date().getTime());
                });
                var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>修改头像成功！</div>');
                $('body').append(alertDiv);
                setTimeout(function () {
                    $(alertDiv).fadeOut('slow');
                }, 3000);
            }
        },
        cache: false,
        contentType: false,
        processData: false
    });
});

// 提交修改信息
$(document).ready(function () {
    $('#editInfoForm').on('submit', function (e) {
        e.preventDefault();  // 阻止表单的默认提交行为

        // 获取表单数据
        var formData = {
            'real_name': $('#modal_real_name').val(),
            'username': $('#modal_username').val(),
            'idcard': $('modal_idcard').val(),
            'birthdate': $('#modal_birthdate').val(),
            'address': $('#modal_address').val(),
            'email': $('#modal_email').val(),
            'phone': $('#modal_phone').val(),
            'job': $('#modal_job').val()
        };

        // 使用AJAX提交表单数据
        $.ajax({
            type: 'POST',
            url: $(this).attr('action'),
            data: formData,
            success: function (response) {
                $('#content').load('UserInfoServlet');
                var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>修改信息成功！</div>');
                $('body').append(alertDiv);
                setTimeout(function () {
                    $(alertDiv).fadeOut('slow');
                }, 3000);
            }
        });
    });
});

//提交修改密码
$(document).ready(function () {
    $('#changePasswordForm').on('submit', function (e) {
        e.preventDefault();  // 阻止表单的默认提交行为

        if ($('#newPassword').val() !== $('#confirmNewPassword').val()) {
            $('#newPassword, #confirmNewPassword').css('border-color', 'red');
            $('#passwordError').text('两次密码不一致！').css('color', 'red');
            return
        }

        // 获取表单数据
        var formData = {
            'newPassword': $('#newPassword').val(),
            'confirmNewPassword': $('#confirmNewPassword').val()
        };
        // 使用AJAX提交表单数据
        $.ajax({
            type: 'POST',
            url: $(this).attr('action'),
            data: formData,
            success: function (response) {
                $('#content').load('UserInfoServlet');
                var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>修改密码成功！</div>');
                $('body').append(alertDiv);
                setTimeout(function () {
                    $(alertDiv).fadeOut('slow');
                }, 3000);
            }
        });
    });
});
