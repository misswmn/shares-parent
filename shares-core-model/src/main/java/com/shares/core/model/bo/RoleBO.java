package com.shares.core.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/26
 * @description
 */
public class RoleBO {
    @NotEmpty(message = "角色名称不能为空")
    private String name;
    @NotNull
//    @Max(value = 40, message = "{role.id.max}")
//    @Min(value = 10, message = "{role.id.min}")
    @Max(4)
    @Min(2)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Class<RoleBO> clazz = RoleBO.class;
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());
    }
}
