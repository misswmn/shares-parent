package com.shares.common.dal.daointerface.auto;

import com.shares.common.dal.dataobject.auto.SysUserDO;
import com.shares.common.dal.dataobject.auto.SysUserDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserDOMapper {
    int countByExample(SysUserDOExample example);

    int deleteByExample(SysUserDOExample example);

    int deleteByPrimaryKey(Long seqId);

    int insert(SysUserDO record);

    int insertSelective(SysUserDO record);

    List<SysUserDO> selectByExample(SysUserDOExample example);

    SysUserDO selectByPrimaryKey(Long seqId);

    int updateByExampleSelective(@Param("record") SysUserDO record, @Param("example") SysUserDOExample example);

    int updateByExample(@Param("record") SysUserDO record, @Param("example") SysUserDOExample example);

    int updateByPrimaryKeySelective(SysUserDO record);

    int updateByPrimaryKey(SysUserDO record);
}