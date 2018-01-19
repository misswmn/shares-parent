package com.shares.common.dal.daointerface;

import com.shares.common.dal.daoobject.UserDO;
import com.shares.common.dal.daoobject.UserParamDO;
import com.shares.common.dal.plugin.common.model.PageRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/2/7
 * @description
 */
@Repository
public interface UserMapper {
    @Update("UPDATE sys_user SET status = 4 WHERE id=#{id}")
    int deleteByPrimaryKey(Long id);

    @Select({
        "<script>" +
        "SELECT " +
            "SEQ_ID seqId," +
            "USER_ID userId," +
            "USERNAME username," +
            "PASSWORD password," +
            "MOBILE mobile," +
            "ADDRESS address," +
            "STATUS status," +
            "CREATE_TIME createTime," +
            "UPDATE_TIME updateTime " +
        "FROM sys_user a WHERE a.IS_DELETE = 'N' " +
                "<if test=\"param != null and param.username != null and param.username != ''\"> and param.username = #{param.username}</if>",
                "<if test=\"param != null and param.password != null and param.password != ''\"> and param.password = #{param.password}</if>" +
        "</script>"
    })
    @ResultType(UserDO.class)
    List<UserDO> listUserByPage(PageRequest<UserParamDO> request);

    @Select("SELECT * FROM sys_user WHERE username = #{user} AND password = #{pwd}")
    UserDO login(@Param("user") String username, @Param("pwd") String password);
}