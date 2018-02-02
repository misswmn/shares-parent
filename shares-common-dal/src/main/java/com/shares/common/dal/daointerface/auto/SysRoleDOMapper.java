package com.shares.common.dal.daointerface.auto;

import com.shares.common.dal.dataobject.auto.SysRoleDO;
import com.shares.common.dal.dataobject.auto.SysRoleDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleDOMapper {
    int countByExample(SysRoleDOExample example);

    int deleteByExample(SysRoleDOExample example);

    int deleteByPrimaryKey(Long seqId);

    int insert(SysRoleDO record);

    int insertSelective(SysRoleDO record);

    List<SysRoleDO> selectByExample(SysRoleDOExample example);

    SysRoleDO selectByPrimaryKey(Long seqId);

    int updateByExampleSelective(@Param("record") SysRoleDO record, @Param("example") SysRoleDOExample example);

    int updateByExample(@Param("record") SysRoleDO record, @Param("example") SysRoleDOExample example);

    int updateByPrimaryKeySelective(SysRoleDO record);

    int updateByPrimaryKey(SysRoleDO record);
}