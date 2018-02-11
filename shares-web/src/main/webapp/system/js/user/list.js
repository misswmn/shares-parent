;$(document).ready(function () {
    var columns = [{field: 'userId', title: '用户编号', width: '5%'},
        {field: 'username', title: '用户名', width: '10%'},
        {field: 'mobile', title: '手机号', width: '10%'},
        {field: 'address', title: '地址', width: '20%'},
        {field: 'status', title: '状态', width: '5%'},
        {
            field: 'createTime', title: '创建时间', width: '15%',
            formatter: function (value, row, index) {
                return shares.dateFormat(new Date(value), "yyyy-MM-dd hh:mm:ss");
            }
        }];

    var $form = $("#user_list_form"),
        $submit = $("#user_list_submit");
    $submit.click(function () {
        renderTable(shares.serializeArray($form), true);
    });

    var renderTable = function (searchParams, refresh) {
        shares.render({
            url: "user_list",
            selector: $("#user_list_table"),
            searchParams: searchParams,
            refresh: refresh,
            columns: columns
        });
    };

    /** init列表 */
    renderTable();
});