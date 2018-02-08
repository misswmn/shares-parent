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
        $("body").mLoading();
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
            $("body").mLoading("hide");
        });
    };
    var getPath = function (url) {
        return domain + url;
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
        }
    }
}();