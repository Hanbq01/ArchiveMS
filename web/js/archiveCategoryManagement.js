function removeModalBackdrop() {
    $('.modal-backdrop').remove();
}

function fillModal(button) {
    document.getElementById('id').value = button.getAttribute('data-id');
    document.getElementById('typeName').value = button.getAttribute('data-typename');
    document.getElementById('typeId').value = button.getAttribute('data-typeId');
    document.getElementById('notes').value = button.getAttribute('data-notes');
    $('#editModal').modal('show');
}

// 修改档案分类的js
$(document).ready(function () {
    $('#editModal').on('submit', function (e) {
        e.preventDefault();  // 阻止表单的默认提交行为

        // 获取表单数据
        var formData = {
            'id': $('#id').val(),
            'typeName': $('#typeName').val(),
            'typeId': $('#typeId').val(),
            'notes': $('#notes').val(),
        };

        // 使用AJAX提交表单数据
        $.ajax({
            type: 'POST',
            url: 'UpdateArchiveCategoryServlet',
            data: formData,
            success: function (response) {
                $('#content').load('ArchiveCategoryManagementServlet');
                var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>修改信息成功！</div>');
                $('body').append(alertDiv);
                setTimeout(function () {
                    $(alertDiv).fadeOut('slow');
                }, 3000);
            }
        });
    });
});

//新增档案分类js
$(document).ready(function () {
    $('#addModal').on('submit', function (e) {
        e.preventDefault();  // 阻止表单的默认提交行为

        // 获取表单数据
        var formData = {
            'typeName': $('#add_typeName').val(),
            'typeId': $('#add_typeId').val(),
            'notes': $('#add_notes').val(),
        };

        // 使用AJAX提交表单数据
        $.ajax({
            type: 'POST',
            url: 'AddArchiveCategoryServlet',
            data: formData,
            success: function (response) {
                $('#content').load('ArchiveCategoryManagementServlet');
                var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>修改信息成功！</div>');
                $('body').append(alertDiv);
                setTimeout(function () {
                    $(alertDiv).fadeOut('slow');
                }, 3000);
            }
        });
    });
});

function showadd(button) {
    $('#addModal').modal('show');
}

function deleteArchiveCategory(id) {
    $.ajax({
        type: 'POST',
        url: 'ArchiveCategoryManagementServlet',
        data: {'id': id, 'action': 'delete'},
        success: function (response) {
            $('#content').load('ArchiveCategoryManagementServlet');
            var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>删除成功！</div>');
            $('body').append(alertDiv);
            setTimeout(function () {
                $(alertDiv).fadeOut('slow');
            }, 3000);
        },
        error: function () {
            var alertDiv = $('<div class="alert alert-danger" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>保存失败！请联系技术人员！</div>');
            $('body').append(alertDiv);
            setTimeout(function () {
                $(alertDiv).fadeOut('slow');
            }, 3000);  // 3秒后自动消失
        }
    });
}
