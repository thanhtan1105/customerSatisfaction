/**
 * Created by TrungNN on 10/29/2016.
 */

var employees;
var list_excellent;
var list_good;
var list_normal;
var list_medium;
var list_bad;

function load_report() {
    var managerId = 1;
    console.info('[managerId]' + managerId);
    var urlString = '/api/emotion/report?managerId=' + managerId;
    $.ajax({
        type: 'GET',
        url: urlString,
        success: function (response) {
            var success = response.success;
            console.info('[success] ' + success);
            if (success) {
                var data = response.data;
                employees = data.listEmployees;
                list_excellent = data.listExcellent;
                list_good = data.listGood;
                list_normal = data.listNormal;
                list_medium = data.listMedium;
                list_bad = data.listBad;
                console.info(employees);
                console.info(list_excellent);
                console.info(list_good);
                console.info(list_medium);
                console.info(list_bad);

                //load chart
                load_chart();
            }
        }
    });
}

function load_chart() {
    /**
     * Initial high chart
     */
    $(function () {
        Highcharts.chart('container', {
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Báo sự hài lòng khách hàng'
            },
            subtitle: {
                text: 'Tháng 10/2016'
            },
            xAxis: {
                categories: employees,
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Khách hàng',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' khách hàng'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                },
                series: {
                    pointWidth: 10
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -40,
                y: 80,
                floating: true,
                borderWidth: 1,
                backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: 'RẤT TỐT',
                data: list_excellent
            }, {
                name: 'TỐT',
                data: list_good
            }, {
                name: 'BÌNH THƯỜNG',
                data: list_normal
            }, {
                name: 'TRUNG BÌNH KHÁ',
                data: list_medium
            }, {
                name: 'CHƯA TỐT',
                data: list_bad
            }]
        });
    });
}

