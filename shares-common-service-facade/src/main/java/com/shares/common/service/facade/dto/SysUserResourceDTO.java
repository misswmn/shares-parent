package com.shares.common.service.facade.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangmn
 * @description
 * @date 2018/2/5 11:05
 */
public class SysUserResourceDTO implements Serializable {
    private static final long serialVersionUID = -9149354338670527905L;
    private String resCode;
    private String resName;
    private String resUrl;
    private String parentCode;
    private String menuCode;
    private String resDesc;
    private String resIcon;
    private String resType;
    private List<SysUserResourceDTO> childNodes;

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

    public List<SysUserResourceDTO> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<SysUserResourceDTO> childNodes) {
        this.childNodes = childNodes;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}
