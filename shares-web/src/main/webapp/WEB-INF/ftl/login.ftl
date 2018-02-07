<#assign base=request.contextPath />
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${base}/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="${base}/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${base}/assets/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/css/custom.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" href="${base}/system/css/jquery.mloading.css" type="text/css">
</head>
<body id="body" class="login">
<div class="logo">
    <img src="assets/img/logo-big.png" alt=""/>
</div>
<div class="content">
    <form id="login-form" action="login" method="post" role="login">
        <h3 class="form-title">Login to your account</h3>
        <div class="error"><span></span></div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">Username</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input class="form-control placeholder-no-fix" type="text" id="username" autocomplete="off"
                       placeholder="用户名" name="username"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">Password</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input class="form-control placeholder-no-fix" type="password" id="password" autocomplete="off"
                       placeholder="密码" name="password"/>
            </div>
        </div>
        <div class="form-actions">
            <label class="checkbox">
                <input type="checkbox" name="remember" value="1"/> 记住我 </label>
            <button type="button" id="login" class="btn green pull-right">
                登录 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
</div>


<script src="${base}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${base}/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${base}/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${base}/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${base}/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${base}/assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="${base}/assets/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${base}/assets/scripts/app.js" type="text/javascript"></script>
<#--<script src="assets/scripts/login-soft.js" type="text/javascript"></script>-->

<script type="text/javascript" src="${base}/system/js/jquery.mloading.js"></script>
<script type="text/javascript" src="${base}/system/js/MD5.js"></script>
<#--<script type="text/javascript" src="system/js/global.js"></script>-->
<script type="text/javascript" src="${base}/system/js/shareshttp.js"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        $.backstretch([
            "assets/img/bg/1.jpg",
            "assets/img/bg/2.jpg",
            "assets/img/bg/3.jpg",
            "assets/img/bg/4.jpg"
        ], {
            fade: 1000,
            duration: 8000
        });
    });
</script>
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