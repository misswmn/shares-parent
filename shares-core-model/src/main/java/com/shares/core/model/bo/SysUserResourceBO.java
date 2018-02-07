package com.shares.core.model.bo;

import java.util.List;

/**
 * @author wangmn
 * @description
 * @date 2018/2/2 15:48
 */
public class SysUserResourceBO {

    private String resCode;
    private String resName;
    private String resUrl;
    private String parentCode;
    private String menuCode;
    private String resDesc;
    private String resIcon;
    private String resType;
    private List<SysUserResourceBO> childNodes;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public String getResIcon() {
        return resIcon;
    }

    public void setResIcon(String resIcon) {
        this.resIcon = resIcon;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public List<SysUserResourceBO> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<SysUserResourceBO> childNodes) {
        this.childNodes = childNodes;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}
