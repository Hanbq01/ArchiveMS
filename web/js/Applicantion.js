$('#applicantForm').submit(function (e) {
    e.preventDefault();
    var r = confirm("确定保存吗？保存后不可更改！请确认信息是否正确！");
    var formData = new FormData(this);
    $.ajax({
        url: $(this).attr('action'),
        type: 'POST',
        data: formData,
        success: function (response) {
            if(response == "success") {
                $('#successMessage').show();
                $('#applicantForm').trigger("reset");
                $('html, body').animate({scrollTop:0}, 'slow');
            } else if(response == "error") {
                $('#errorMessage').show();
                $('html, body').animate({scrollTop:0}, 'slow');
            }
        },
        cache: false,
        contentType: false,
        processData: false
    });
});
