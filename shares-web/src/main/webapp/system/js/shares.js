var shares = function () {
    var domain = "http://mreagle.cn:8080/shares";
    var action = {
        "login": "/user/login",
        "home": "/main",
        "user_list": "/user/list"
    };
    var post = function (url, data, cb, autoHandle) {
        var path = getPath(url);
        var reject = function (reason) {
            var dfr = $.Deferred();
            dfr.reject(reason);
            return dfr.promise();
        };
        loading();
        $.ajax(path, {data: data, type: "POST"})
            .then(function (response) {
                if (autoHandle === undefined || autoHandle === true) {
                    if (response.code === 0) {
                        typeof cb === 'function' && cb(response);
                    } else {
                        alert(response.message || "系统繁忙");
                        reject(response);
                    }
                } else {
                    typeof cb === 'function' && cb(response);
                }
            }, function (xhr) {
                alert("网络错误");
                reject(xhr);
            }).always(function () {
            $.closeLoading("loading");
        });
    };
    var getPath = function (url) {
        return domain + url;
    };
    var loading = function (target, loadingName) {
        $(target || 'body').loading({
            loadingWidth: 120,
            title: '',
            name: loadingName || 'loading',
            discription: '',
            direction: 'column',
            type: 'origin',
            // originBg:'#71EA71',
            originDivWidth: 40,
            originDivHeight: 40,
            originWidth: 6,
            originHeight: 6,
            smallLoading: false,
            loadingMaskBg: 'rgba(0,0,0,0.2)'
        });
    };

    return {
        login: function (data, cb) {
            post(action.login, data, cb, false);
        },
        toHome: function () {
            location.href = getPath(action.home);
        },
        jump: function (url, data) {
            location.href = getPath("/page/" + url) + "?data=" + encodeURI(data || "");
        },
        userList: function (option, cb) {
            post(action.user_list, option, cb);
        },
        serializeArray: function (selector) {
            var serializeObj = {},
                array = selector.serializeArray();
            $(array).each(function () {
                if (serializeObj[this.name]) {
                    serializeObj[this.name] += ';' + this.value;
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
            return serializeObj;
        },
        render: function (conf) {
            if (!conf) {
                return;
            }
            if (!action[conf.url]) {
                return;
            }
            if (!conf.selector) {
                return;
            }
            var condition = function (params) {
                return {
                    page: params.pageNumber,
                    count: params.pageSize,
                    param: conf.searchParams || {}
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
            var $table = conf.selector;
            var default_options = {
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
                clickToSelect: true
            };
            var custom_option = {
                url: getPath(action[conf.url]),
                columns: conf.columns
            };
            if (conf.refresh === undefined || conf.refresh === false) {
                $table.bootstrapTable($.extend({}, custom_option, default_options));
            } else {
                $table.bootstrapTable("refresh", {
                    url: getPath(action[conf.url]),
                    query: conf.searchParams || {}
                });
            }
        },
        dateFormat: function (date, fmt) {
            var format = function (date, fmt) {
                var o = {
                    "M+": date.getMonth() + 1,               //月份
                    "d+": date.getDate(),                    //日
                    "h+": date.getHours(),                   //小时
                    "m+": date.getMinutes(),                 //分
                    "s+": date.getSeconds(),                 //秒
                    "q+": Math.floor((date.getMonth() + 3) / 3), //季度
                    "S": date.getMilliseconds()             //毫秒
                };
                if (/(y+)/.test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
                }
                for (var k in o) {
                    if (new RegExp("(" + k + ")").test(fmt)) {
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                    }
                }
                return fmt;
            };
            return format(date, fmt);
        },
        loading: function (target) {
            loading(target, undefined);
        },
        closeLoading: function (target) {
            $('body').closeLoading(target);
        }
    }
}();