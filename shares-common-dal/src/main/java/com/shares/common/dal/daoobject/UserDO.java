package com.shares.common.dal.daoobject;

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
public class UserDO implements Serializable {
    private static final long serialVersionUID = -3827268739415842545L;
    private Long seqId;
    private String userId;
    private String username;
    private String password;
    private boolean rememberMe;
    private String mobile;
    private String address;
    private String status;
    private String isDelete;
    private Date createTime;
    private Date updateTime;
    private String comments;
}
