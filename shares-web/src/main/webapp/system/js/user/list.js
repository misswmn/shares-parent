;$(document).ready(function () {
    var columns = [{
        field: 'seqId',
        title: '序号'
    }, {
        field: 'seqId',
        title: '用户编号'
    }, {
        field: 'username',
        title: '用户名'
    }, {
        field: 'mobile',
        title: '手机号'
    }, {
        field: 'address',
        title: '地址'
    }, {
        field: 'status',
        title: '状态'
    }, {
        field: 'createTime',
        title: '创建时间'
    }];


    var condition = function (params) {
        return {
            page: params.pageNumber,
            count: params.pageSize,
            param: {}
        }
    };
    var respHandler = function (resp) {
        var result = {};
        if (!resp || resp.code !== 0) return result;
        var content = resp.content || {};
        result.total = content.total || 0;
        result.rows = content.rows || [];
        return result;
    };
    var $table = $("#user_list_table");
    $table.bootstrapTable({
        url: "/shares/user/list",
        contentType: "application/json",
        method: "post",
        dataType: "json",
        queryParamsType: '',
        queryParams: condition,
        cache: false,
        pagination: true,
        sidePagination: "server",
        pageNumber: 1,
        pageSize: 10,
        responseHandler: respHandler,
        clickToSelect: true,
        columns: columns
    });


});