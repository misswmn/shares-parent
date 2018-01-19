;$(function () {
    var domain = "http://localhost:8080/shares";
    var url = {"login": "/user/login"};
    window.shareshttp = {
        post: function (url, data, cb) {
            var dtd = $.Deferred();
            var path = this.getPath(url);
            var task = function (dtd) {
                $.ajax(path, {data: data, type: "POST"}).done(function (data) {
                    typeof cb === 'function' && cb(data);
                    dtd.resolve();
                }).fail(function (data) {
                    alert("系统繁忙,请稍后重试");
                    dtd.reject();
                }).always(function () {
                    $("body").mLoading("hide");
                });
                return dtd;
            };
            $("body").mLoading();
            task(dtd);
        },
        getPath: function (url) {
            return domain + url;
        },
        login: function (data, cb) {
            this.post(url.login, data, cb);
        }
    };
});