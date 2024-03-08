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
    document.getElementById('review_note').value = button.getAttribute('data-review_note');

    $('#myModal').on('hidden.bs.modal', function (e) {
        $('.modal-backdrop').remove();
    });
}


//封装成一个函数用于刷新局部页面
function loadMyAppointments() {
    $('#content').load('myAppointmentServlet');
}

function cancelAppointment(id) {
    var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf('/', 2));
    $.ajax({
        url: contextPath + '/myAppointmentServlet',
        type: 'POST',
        data: {'id': id, 'action': 'cancel'},
        success: function (response) {
            loadMyAppointments();
            // 新增的 Bootstrap 5 alert 提示框
            var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>取消预约成功！</div>');
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

//移除模态框背景色
function removeModalBackdrop() {
    $('.modal-backdrop').remove();
}
