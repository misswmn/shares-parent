<#assign base=request.contextPath />
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="stylesheet" href="system/css/reset.css" type="text/css">
    <link rel="stylesheet" href="system/css/supersized.css" type="text/css">
    <link rel="stylesheet" href="system/css/style.css" type="text/css">
    <link rel="stylesheet" href="system/bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="system/css/jquery.mloading.css" type="text/css">
</head>
<body id="body">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4"></div>

        <div class="col-md-4">
            <com.shares.common.util.section class="login-form">
                <form id="_form" action="login" method="post" role="login">
                    <img src="system/images/logo.png" class="img-responsive" alt=""/>
                    <div class="form-group">
                        <input type="text" id="username" class="form-control input-lg" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <input type="password" id="password" class="form-control input-lg" placeholder="密码">
                    </div>
                    <div class="checkbox">
                        <label> <input type="checkbox" checked="checked" id="rememberMe">记住我 </label>
                    </div>
                    <button type="button" class="btn btn-lg btn-primary btn-block" id="login">登录</button>
                    <div>
                        <a href="#">Create account</a> or <a href="#">reset password</a>
                    </div>
                    <div class="error"><span></span></div>
                </form>
            </com.shares.common.util.section>
        </div>

        <div class="col-md-4"></div>
    </div>
</div>
<script type="text/javascript" src="system/js/jquery.js"></script>
<script type="text/javascript" src="system/js/jquery.mloading.js"></script>
<script type="text/javascript" src="system/js/MD5.js"></script>
<script type="text/javascript" src="system/js/global.js"></script>
<script type="text/javascript" src="system/js/shareshttp.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var $login = $("#login");
        $login.click(function () {
            var username = $("#username").val();
            var password = $("#password").val();
            var $error = $(".error");
            if (username === '') {
                $error.fadeOut("fast", function () {
                    $error.css("top", "27px").show();
                });
                $error.fadeIn("fast", function () {
                    $("#username").focus();
                });
                return false;
            }
            if (password === '') {
                $error.fadeOut('fast', function () {
                    $error.css('top', '96px').show();
                });
                $(this).find('.error').fadeIn('fast', function () {
                    $('.password').focus();
                });
                return false;
            }
            var data = {
                username: username,
                password: MD5(password),
                rememberMe: $("#rememberMe").is(":checked")
            };

            shareshttp.login(data, function (data) {
                if (data.code === 0) {
                    shareshttp.toHome();
                } else {
                    $error.html(data.message || "登录失败");
                    $("#username").focus();
                    $("#password").val("");
                }
            });
        });
        document.onkeydown = function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.keyCode === 13) $login.click();
        }
    })
</script>
</body>
</html>