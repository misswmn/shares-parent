<div class="tab-pane active" id="tab_2">
    <div class="portlet box green">
        <div class="portlet-title">
            <div class="caption">
                <i class="fa fa-reorder"></i>用户查询
            </div>
            <div class="tools">
                <a href="javascript:;" class="collapse"></a>
            </div>
        </div>
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form action="#" class="form-horizontal" id="user_list_form">
                <div class="form-body">
                    <!--/row-->
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label col-md-3">用户名</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="username">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label col-md-3">手机号</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="mobile">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label col-md-3">角色</label>
                                <div class="col-md-9">
                                    <select class="form-control">
                                        <option>管理员</option>
                                        <option>人事专员</option>
                                        <option>行政专员</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <!--/span-->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label col-md-3">State</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                        </div>
                        <!--/span-->
                    </div>
                </div>
                <div class="form-actions fluid">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" class="btn green" id="user_list_submit">查询</button>
                                <button type="button" class="btn default">重置</button>
                            </div>
                        </div>
                        <div class="col-md-6">
                        </div>
                    </div>
                </div>
            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>

<div class="portlet box green">
    <div class="portlet-title">
        <div class="caption">
            用户列表
        </div>
    </div>
    <div class="portlet-body">
        <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
            <div class="table-scrollable">
                <table id="user_list_table"
                       data-classes="table table-striped table-bordered table-hover">

                </table>
            </div>
        </div>
    </div>
</div>

<script src="${base}/system/js/user/list.js"></script>