</div>
<!-- END CONTENT BODY -->
</div>
<!-- END CONTENT -->
<!-- BEGIN QUICK SIDEBAR -->
<a href="javascript:;" class="page-quick-sidebar-toggler">
    <i class="icon-login"></i>
</a>
<!-- END QUICK SIDEBAR -->
</div>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<#--<script src="${base}/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>-->
<script src="${base}/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<#--<script src="${base}/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>-->
<script src="${base}/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${base}/assets/scripts/app.js" type="text/javascript"></script>
<#--<script src="assets/scripts/index.js" type="text/javascript"></script>-->
<#--<script src="assets/scripts/tasks.js" type="text/javascript"></script>-->
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function() {
        App.init(); // initlayout and core plugins
        // Index.init();
        // Index.initJQVMAP(); // init index page's custom scripts
        // Index.initCalendar(); // init index page's custom scripts
        // Index.initCharts(); // init index page's custom scripts
        // Index.initChat();
        // Index.initMiniCharts();
        // Index.initDashboardDaterange();
        // Index.initIntro();
        // Tasks.initDashboardWidget();
    });
</script>
</body>
</html>