package com.shares.common.dal.daointerface.auto;

import com.shares.common.dal.dataobject.auto.SysRoleResourceDO;
import com.shares.common.dal.dataobject.auto.SysRoleResourceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleResourceDOMapper {
    int countByExample(SysRoleResourceDOExample example);

    int deleteByExample(SysRoleResourceDOExample example);

    int deleteByPrimaryKey(Long seqId);

    int insert(SysRoleResourceDO record);

    int insertSelective(SysRoleResourceDO record);

    List<SysRoleResourceDO> selectByExample(SysRoleResourceDOExample example);

    SysRoleResourceDO selectByPrimaryKey(Long seqId);

    int updateByExampleSelective(@Param("record") SysRoleResourceDO record, @Param("example") SysRoleResourceDOExample example);

    int updateByExample(@Param("record") SysRoleResourceDO record, @Param("example") SysRoleResourceDOExample example);

    int updateByPrimaryKeySelective(SysRoleResourceDO record);

    int updateByPrimaryKey(SysRoleResourceDO record);
}