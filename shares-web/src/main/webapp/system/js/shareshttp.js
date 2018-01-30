;$(function () {
    var domain = "http://mreagle.cn:8080/shares";
    var url = {"login": "/user/login"};
    window.shareshttp = {
        post: function (url, data, cb) {
            var path = this.getPath(url);
            var reject = function (reason) {
                var dfr = $.Deferred();
                dfr.reject(reason);
                return dfr.promise();
            };
            $("body").mLoading();
            $.ajax(path, {data: data, type: "POST"})
                .then(function (response) {
                    if (response.code === 0) {
                        typeof cb === 'function' && cb(response);
                    } else {
                        alert(response.message || "系统错误");
                        reject(response);
                    }
                }, function (xhr) {
                    alert("网络错误");
                    reject({
                        xhr: xhr
                    });
                }).always(function () {
                $("body").mLoading("hide");
            });
        },
        getPath: function (url) {
            return domain + url;
        },
        login: function (data, cb) {
            this.post(url.login, data, cb);
        }
    };
});