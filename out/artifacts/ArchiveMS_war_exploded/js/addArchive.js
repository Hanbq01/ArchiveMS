$('#addArchiveForm').submit(function (e) {
    e.preventDefault();
    var r = confirm("是否保存？")
    var formData = new FormData(this);
    $.ajax({
        url: $(this).attr('action'),
        type: 'POST',
        data: formData,
        dataType: 'text',
        success: function (response) {
            if (response.trim() == "success") {
                $('#addArchiveForm').trigger("reset"); // 清空所有的输入框
                var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>保存成功！</div>');
                $('body').append(alertDiv);
                setTimeout(function () {
                    $(alertDiv).fadeOut('slow');
                }, 3000);  // 3秒后自动消失
            } else if (response.trim() == "error") {
                $('#errorMessage').show();
                var alertDiv = $('<div class="alert alert-danger" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>保存失败！请联系技术人员！</div>');
                $('body').append(alertDiv);
                setTimeout(function () {
                    $(alertDiv).fadeOut('slow');
                }, 3000);  // 3秒后自动消失
            }else if (response.trim()=="fkerror"){
                $('#errorMessage').show();
                var alertDiv = $('<div class="alert alert-danger" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>不存在此人！请重新检查此人是否存在！</div>');
                $('body').append(alertDiv);
                setTimeout(function () {
                    $(alertDiv).fadeOut('slow');
                }, 3000);
            }
        },
        cache: false,
        contentType: false,
        processData: false
    })
});