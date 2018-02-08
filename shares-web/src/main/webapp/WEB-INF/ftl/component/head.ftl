<#assign base=request.contextPath />
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>后台管理平台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${base}/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="${base}/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
<#--<link href="${base}/assets/css/pages/tasks.css" rel="stylesheet" type="text/css"/>-->
    <link href="${base}/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${base}/assets/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME LAYOUT STYLES -->
    <!-- BOOTSTRAP TABLE BEGIN -->
    <link href="${base}/assets/plugins/bootstrap-table/css/bootstrap-table.css"/>
    <link href="${base}/system/css/jquery.mloading.css" rel="stylesheet" type="text/css">
    <!-- BOOTSTRAP TABLE EDN -->
    <link rel="shortcut icon" href="favicon.ico"/>

    <script src="${base}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${base}/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <script src="${base}/assets/plugins/bootstrap-table/js/bootstrap-table.js"></script>
    <script src="${base}/assets/plugins/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>
    <script src="${base}/system/js/jquery.mloading.js" type="text/javascript"></script>
</head>
<body class="page-header-fixed">
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">
    <!-- BEGIN TOP NAVIGATION BAR -->
    <div class="header-inner">
        <!-- BEGIN LOGO -->
        <a class="navbar-brand" href="index.html">
            <img src="${base}/assets/img/logo.png" alt="logo" class="img-responsive"/>
        </a>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <img src="${base}/assets/img/menu-toggler.png" alt=""/>
        </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
    </div>
    <!-- END TOP NAVIGATION BAR -->
</div>
<div class="clearfix"></div>
<div class="page-container">
    <!-- BEGIN SIDEBAR -->
    <div class="page-sidebar-wrapper">
        <!-- BEGIN SIDEBAR -->
        <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
        <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
        <div class="page-sidebar navbar-collapse collapse">
            <!-- BEGIN SIDEBAR MENU -->
            <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
            <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
            <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
            <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
            <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
            <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
            <#include "menu.ftl">
            <!-- END SIDEBAR MENU -->
            <!-- END SIDEBAR MENU -->
        </div>
        <!-- END SIDEBAR -->
    </div>
    <!-- END SIDEBAR -->
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <!-- BEGIN CONTENT BODY -->
        <div class="page-content">
            <#include "crumb.ftl">
