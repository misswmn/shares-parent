
<ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true"
    data-slide-speed="200" style="padding-top: 20px">
    <li class="sidebar-toggler-wrapper">
        <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
        <div class="sidebar-toggler hidden-phone">
        </div>
        <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
    </li>
    <#macro menuList menuId menudata>
        <#if menudata.childNodes?? && (menudata.childNodes?size>0)>
            <li class="<#if menuId!=''&&menuId?starts_with(menudata.menuCode)> open </#if>">
                <a href="javascript:;" onclick='jump("${base}","${menudata.resUrl!default("")}","${menudata.menuCode}")'>
                    <i class="${menudata.resIcon!''}"></i>
                    <span class="title">${menudata.resName}</span>
                    <span class=" arrow <#if menuId!=''&&menuId?starts_with(menudata.menuCode)> open </#if>"></span>
                </a>
                <ul class="sub-menu" style="<#if menuId!=''&&menuId?starts_with(menudata.menuCode)> display:block; </#if>">
                    <#list menudata.childNodes as menu>
                        <@menuList menuId=menuId menudata=menu />
                    </#list>
                </ul>
            </li>
        <#else>
            <li class="<#if menuId!=''&&menuId?starts_with(menudata.menuCode)> open </#if>">
                <a href="javascript:;" onclick='jump("${base}","${menudata.resUrl!default("")}","${menudata.menuCode}")'>
                    <i class="${menudata.resIcon!''}"></i>
                    <span class="title">${menudata.resName}</span>
                </a>
            </li>
        </#if>
    </#macro>

    <#if menuTree?? && (menuTree?size>0)>
        <#list menuTree as menu>
            <@menuList menuId=data menudata=menu></@menuList>
        </#list>
    </#if>

</ul>


<script type="text/javascript">
    function jump(base, url, menu) {
        if (!url) {
            return;
        }
        location.href = base + "/page" + "?page=" + url + "&data=" + encodeURI(menu);
    }
</script>