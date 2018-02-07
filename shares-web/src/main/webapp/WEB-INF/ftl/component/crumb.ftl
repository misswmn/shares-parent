<div class="row">
    <div class="col-md-12">
        <!-- BEGIN PAGE TITLE & BREADCRUMB-->
        <h3 class="page-title">
        </h3>
        <#if menuCrumb??&&(menuCrumb?size>0)>
            <ul class="page-breadcrumb breadcrumb">
            <#list menuCrumb as menu>
                <li>
                    <i class="${menu.resIcon!}"></i>
                    <a href="javascript:;">${menu.resName}</a>
                    <#if menu_has_next>
                        <i class="fa fa-angle-right"></i>
                    </#if>
                </li>
            </#list>
            </ul>
        </#if>
        <!-- END PAGE TITLE & BREADCRUMB-->
    </div>
</div>