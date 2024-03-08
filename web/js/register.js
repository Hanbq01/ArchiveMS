$(document).ready(function() {
    $('#registerForm').on('submit', function(event) {
        event.preventDefault(); // 阻止表单的默认提交行为

        // 检查两个密码输入框的内容是否相同
        if ($('#password').val() !== $('#confimpwd').val()) {
            // 如果不同，标红并更改旁边的span的内容
            $('#password, #confimpwd').css('border-color', 'red');
            $('#password').next('span').text('请确认两次输入是否相同！').css('color','red');
            $('#confimpwd').next('span').text('请确认两次输入是否相同！').css('color','red');
            return; // 阻止后续代码的执行
        }

        $.ajax({
            type: 'POST',
            url: $(this).attr('action'),
            data: $(this).serialize(),
            success: function(response) {
                if (response === 'success') {
                    $('#successMessage').show(); // 显示成功消息
                    $('#errorMessage').hide(); // 隐藏错误消息

                    // 清除输入框的内容
                    $('#real_name').val('');
                    $('#username').val('');
                    $('#password').val('');
                    $('#confimpwd').val('');
                    $('#birthdate').val('');
                    $('#address').val('');
                    $('#email').val('');
                    $('#phone').val('');
                } else {
                    $('#errorMessage').show(); // 显示错误消息
                    $('#successMessage').hide(); // 隐藏成功消息
                }
            },
            error: function() {
                $('#errorMessage').show(); // 显示错误消息
                $('#successMessage').hide(); // 隐藏成功消息
            }
        });
    });
});
