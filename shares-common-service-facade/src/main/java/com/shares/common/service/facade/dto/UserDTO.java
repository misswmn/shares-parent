package com.shares.common.service.facade.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/27
 * @description
 */
public class UserDTO {
    @NotEmpty(message = "{common.param.illegal}")
    private List<String> roles;
    private String username;
    private String password;
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
