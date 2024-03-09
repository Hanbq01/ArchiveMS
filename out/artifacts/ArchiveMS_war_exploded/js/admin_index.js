$(document).ready(function () {
    // 默认显示 home.jsp
    $('#content').load('home.jsp');

    $('#home').click(function () {
        $('#content').load('home.jsp');//主页
    });
    $('#addArchive').click(function () {
        $('#content').load('addArchive.jsp');//新增档案
    });
    $('#archiveCategoryManagement').click(function () {
        $('#content').load('ArchiveCategoryManagementServlet');//档案分类管理
    });
    $('#archiveManagement').click(function () {
        $('#content').load('ArchiveManagementServlet');//档案管理
    });
    $('#archiveFilingManagement').click(function () {
        $('#content').load('ArchiveFilingServlet');//归档档案管理
    });
    $('#ApplicationManagement').click(function () {
        $('#content').load('ApplicationManagementServlet');//预约管理
    });
    $('#managementPersonnel').click(function () {
        $('#content').load('ManagementPersonnelServlet');//管理人员
    });
    $('#chart').click(function () {
        $('#content').load('chart.jsp');//管理人员
    });

    // 鼠标悬停显示下拉菜单
    $('.dropdown').hover(function () {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
    }, function () {
        $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
    });
});
