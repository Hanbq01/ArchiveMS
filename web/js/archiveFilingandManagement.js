//删除档案的按钮操作
function deleteArchive(id) {
    $.ajax({
        type: 'POST',
        url: 'ArchiveFilingServlet',
        data: {'id': id, 'action': 'delete'},
        success: function (response) {
            $('#content').load('ArchiveFilingServlet');
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

//还原档案的按钮操作
function restoreArchive(id) {
    $.ajax({
        type: 'POST',
        url: 'ArchiveFilingServlet',
        data: {'id': id, 'action': 'restore'},
        success: function (response) {
            $('#content').load('ArchiveFilingServlet');
            var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>归档成功！</div>');
            $('body').append(alertDiv);
            setTimeout(function () {
                $(alertDiv).fadeOut('slow');
            }, 3000);
        },
        error: function () {
            var alertDiv = $('<div class="alert alert-danger" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>操作失败！请联系技术人员！</div>');
            $('body').append(alertDiv);
            setTimeout(function () {
                $(alertDiv).fadeOut('slow');
            }, 3000);  // 3秒后自动消失
        }
    });
}

//归档档案的按钮操作
function filingArchive(id) {
    $.ajax({
        type: 'POST',
        url: 'ArchiveManagementServlet',
        data: {'id': id, 'action': 'filing'},
        success: function (response) {
            $('#content').load('ArchiveManagementServlet');
            var alertDiv = $('<div class="alert alert-success" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>归档成功！</div>');
            $('body').append(alertDiv);
            setTimeout(function () {
                $(alertDiv).fadeOut('slow');
            }, 3000);
        },
        error: function () {
            var alertDiv = $('<div class="alert alert-danger" role="alert" style="width: 200px; position: fixed; top: 20px; right: 20px;"><h5><strong>提示</strong></h5>操作失败！请联系技术人员！</div>');
            $('body').append(alertDiv);
            setTimeout(function () {
                $(alertDiv).fadeOut('slow');
            }, 3000);  // 3秒后自动消失
        }
    });
}