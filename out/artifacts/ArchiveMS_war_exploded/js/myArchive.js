function openModal(id, name, year, notes) {
    $('#archiveId').val(id);
    $('#archiveName').val(name);
    $('#archiveYear').val(year);
    $('#archiveNotes').val(notes);
    $('#editModal').modal('show');
    $('.modal-backdrop').remove();
}

// 将AJAX请求放在一个函数中
$(document).ready(function () {
    $('#editForm').on('submit', function (e) {
        e.preventDefault();  // 阻止表单的默认提交行为
        var formData = {
            'id': $('#archiveId').val(),
            'name': $('#archiveName').val(),
            'year': $('#archiveYear').val(),
            'notes': $('#archiveNotes').val()
        };

        $.ajax({
            type: 'POST',
            url: $('#editForm').attr('action'),
            data: formData,
            success: function (response) {
                if (response === "success") {
                    $('#content').load('myArchiveServlet');
                    var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>修改信息成功！</div>');
                    $('body').append(alertDiv);
                    setTimeout(function () {
                        $(alertDiv).fadeOut('slow');
                    }, 3000);
                }
            },
        });
    });
});
