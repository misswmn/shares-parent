package com.shares.common.dal.daointerface.auto;

import com.shares.common.dal.dataobject.auto.SysUserRoleDO;
import com.shares.common.dal.dataobject.auto.SysUserRoleDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleDOMapper {
    int countByExample(SysUserRoleDOExample example);

    int deleteByExample(SysUserRoleDOExample example);

    int deleteByPrimaryKey(Long seqId);

    int insert(SysUserRoleDO record);

    int insertSelective(SysUserRoleDO record);

    List<SysUserRoleDO> selectByExample(SysUserRoleDOExample example);

    SysUserRoleDO selectByPrimaryKey(Long seqId);

    int updateByExampleSelective(@Param("record") SysUserRoleDO record, @Param("example") SysUserRoleDOExample example);

    int updateByExample(@Param("record") SysUserRoleDO record, @Param("example") SysUserRoleDOExample example);

    int updateByPrimaryKeySelective(SysUserRoleDO record);

    int updateByPrimaryKey(SysUserRoleDO record);
}