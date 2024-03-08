function fillModal(button) {
    document.getElementById('id').value = button.getAttribute('data-id');
    document.getElementById('realName').value = button.getAttribute('data-realName');
    document.getElementById('idCard').value = button.getAttribute('data-idCard');
    document.getElementById('username').value = button.getAttribute('data-username');
    document.getElementById('birthdate').value = button.getAttribute('data-birthdate');
    document.getElementById('address').value = button.getAttribute('data-address');
    document.getElementById('email').value = button.getAttribute('data-email');
    document.getElementById('phone').value = button.getAttribute('data-phone');
    document.getElementById('job').value = button.getAttribute('data-job');
}

//移除模态框背景色
function removeModalBackdrop() {
    $('.modal-backdrop').remove();
}

function submitForm() {
    // 获取表单数据
    var id = document.getElementById('id').value;
    var realName = document.getElementById('realName').value;
    var idCard = document.getElementById('idCard').value;
    var username = document.getElementById('username').value;
    var birthdate = document.getElementById('birthdate').value;
    var address = document.getElementById('address').value;
    var email = document.getElementById('email').value;
    var phone = document.getElementById('phone').value;
    var job = document.getElementById('job').value;

    // 创建一个对象来存储表单数据
    var data = {
        'id': id,
        'realName': realName,
        'idCard': idCard,
        'username': username,
        'birthdate': birthdate,
        'address': address,
        'email': email,
        'phone': phone,
        'job': job
    };

    // 使用Ajax发送POST请求
    $.ajax({
        type: 'POST',
        url: 'ManagementPersonnelUpdateServlet',
        data: data,
        success: function (response) {
            $('#content').load('ManagementPersonnelServlet'); // 刷新页面
            var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>提交成功！</div>');
            $('body').append(alertDiv);
            setTimeout(function () {
                $(alertDiv).fadeOut('slow');
            }, 3000);  // 3秒后自动消失
        },
        error: function (error) {
            // 这里可以添加失败后的操作
            console.log('Error:', error);
        }
    });
}

// 在“保存更改”按钮的点击事件中调用submitForm函数
document.querySelector('.btn-primary').addEventListener('click', submitForm);

function deleteUser(id) {
    $.ajax({
        type: 'POST',
        url: 'ManagementPersonnelServlet',
        data: {'id': id, 'action': 'delete'},
        success: function (response) {
            $('#content').load('ManagementPersonnelServlet');
            var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>操作成功！</div>');
            $('body').append(alertDiv);
            setTimeout(function () {
                $(alertDiv).fadeOut('slow');
            }, 3000);  // 3秒后自动消失
        },
        error: function (error) {
            var alertDiv = $('<div class="alert alert-danger" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>操作失败！</div>');
            $('body').append(alertDiv);
            setTimeout(function () {
                $(alertDiv).fadeOut('slow');
            }, 3000);  // 3秒后自动消失
        }
    });
}
