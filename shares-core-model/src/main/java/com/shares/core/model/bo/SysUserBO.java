package com.shares.core.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/4/26
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserBO implements Serializable {
    private static final long serialVersionUID = -3827268739415842545L;
    private String userId;
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z]{5,20}$", message = "用户名格式不正确")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    private String mobile;
    private String address;
    private String status;
    private Date createTime;
    private Date updateTime;
}
