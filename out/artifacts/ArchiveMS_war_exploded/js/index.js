$(document).ready(function() {
    // 默认显示 home.jsp
    $('#content').load('home.jsp');

    $('#home').click(function() {
        hideModal();
        $('#content').load('home.jsp');//主页
    });
    $('#onlineApplication').click(function() {
        hideModal();
        $('#content').load('onlineApplication.jsp');//在线申请
    });
    $('#onSiteAppointment').click(function() {
        hideModal();
        $('#content').load('onSiteAppointment.jsp');//到馆申请
    });
    $('#myAppointment').click(function() {
        hideModal();
        $('#content').load('myAppointmentServlet');//我的预约
    });
    $('#userinfo').click(function() {
        hideModal();
        $('#content').load('UserInfoServlet');//个人信息
    });
    $('#myArchive').click(function() {
        hideModal();
        $('#content').load('myArchiveServlet');//我的档案
    });

    // 鼠标悬停显示下拉菜单
    $('.dropdown').hover(function() {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
    }, function() {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
    });
});

function hideModal() {
    $('.modal').modal('hide');
    $('body').removeClass('modal-open');
    $('.modal-backdrop').remove();
    $('.modal-backdrop.fade.show').hide(); // 新增的代码
}
