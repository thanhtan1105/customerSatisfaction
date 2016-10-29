/**
 * Created by TrungNN on 10/29/2016.
 */

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
            categories: ['Nguyen Vu Linh', 'Le Thanh Tan', 'Nguyen Van Manh', 'Nguyen Nang Trung', 'Thai Quang Hien'],
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
            data: [107, 31, 65, 203, 2]
        }, {
            name: 'TỐT',
            data: [133, 156, 47, 48, 6]
        }, {
            name: 'BÌNH THƯỜNG',
            data: [152, 254, 42, 74, 38]
        }, {
            name: 'TRUNG BÌNH KHÁ',
            data: [142, 85, 50, 40, 138]
        }, {
            name: 'CHƯA TỐT',
            data: [122, 22, 40, 70, 38]
        }]
    });
});
