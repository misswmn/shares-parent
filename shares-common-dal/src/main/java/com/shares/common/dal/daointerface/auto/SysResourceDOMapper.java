package com.shares.common.dal.daointerface.auto;

import com.shares.common.dal.dataobject.auto.SysResourceDO;
import com.shares.common.dal.dataobject.auto.SysResourceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysResourceDOMapper {
    int countByExample(SysResourceDOExample example);

    int deleteByExample(SysResourceDOExample example);

    int deleteByPrimaryKey(Long seqId);

    int insert(SysResourceDO record);

    int insertSelective(SysResourceDO record);

    List<SysResourceDO> selectByExample(SysResourceDOExample example);

    SysResourceDO selectByPrimaryKey(Long seqId);

    int updateByExampleSelective(@Param("record") SysResourceDO record, @Param("example") SysResourceDOExample example);

    int updateByExample(@Param("record") SysResourceDO record, @Param("example") SysResourceDOExample example);

    int updateByPrimaryKeySelective(SysResourceDO record);

    int updateByPrimaryKey(SysResourceDO record);
}