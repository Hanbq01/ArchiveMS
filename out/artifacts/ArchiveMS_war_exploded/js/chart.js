var typeChart = echarts.init(document.getElementById('typeChart'));
var birthChart = echarts.init(document.getElementById('birthChart'));
var addressChart = echarts.init(document.getElementById('addressChart'));

function loadData(chart, url) {
    $.get(url, function (data) {
        chart.setOption({
            series: [{
                type: 'pie',
                data: data
            }]
        });
    });
}

loadData(typeChart, 'TypeServlet');
loadData(birthChart, 'BirthServlet');
loadData(addressChart, 'AddressServlet');
