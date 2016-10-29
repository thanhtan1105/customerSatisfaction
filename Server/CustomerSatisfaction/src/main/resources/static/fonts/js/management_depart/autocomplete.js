/**
 * Created by TrungNN on 10/18/2016.
 */

var dataSearch = [
    {id: 1, name: 'Toronto1'},
    {id: 2, name: 'Montreal'},
    {id: 3, name: 'New York'},
    {id: 4, name: 'Buffalo'},
    {id: 5, name: 'Boston'},
    {id: 6, name: 'Columbus'},
    {id: 7, name: 'Dallas'},
    {id: 8, name: 'Vancouver'},
    {id: 9, name: 'Seattle'},
    {id: 10, name: 'Los Angeles'}
];

var resultSearch;

$('#btn-test').on('click', function () {
    alert('hello');
});

$.mockjax({
    url: '/departments/list',
    response: function (query) {
        var code = query.data.query;
        console.info('query: ' + code);
        var urlString = '/api/department/search?code=' + code
            + '&name=' + code;
        $.ajax({
            type: "GET",
            url: urlString,
            success: function (response) {
                var departmentList = response.data.content;
                resultSearch = new Array();
                if (departmentList != null) {
                    for (var i = 0; i < departmentList.length; i++) {
                        console.info('department: ' + departmentList[i].name);
                        resultSearch.push({name: departmentList[i].name + ' - ' + departmentList[i].code});
                    }
                }
                console.info('success: ' + response.data);
                console.info('data: ' + response.data.content[0].name);
                console.info('result search: ' + resultSearch);
                console.info('result search: ' + dataSearch);
            }
        });
        this.responseText = resultSearch;
    }
});

$('#demo4').typeahead({
    ajax: {
        url: '/departments/list',
        triggerLength: 2
    }
});

