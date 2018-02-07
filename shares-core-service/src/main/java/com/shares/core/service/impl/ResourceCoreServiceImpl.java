package com.shares.core.service.impl;

import com.shares.common.dal.daointerface.SysUserInfoDOMapper;
import com.shares.common.dal.dataobject.SysUserResourceInfoDO;
import com.shares.common.util.JsonUtils;
import com.shares.core.model.bo.SysUserResourceBO;
import com.shares.core.model.enums.ResourceCodeEnum;
import com.shares.core.model.enums.ResourceTypeEnum;
import com.shares.core.service.ResourceCoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author wangmn
 * @description
 * @date 2018/2/2 15:50
 */
@Service
public class ResourceCoreServiceImpl implements ResourceCoreService {
    @Inject
    private SysUserInfoDOMapper sysUserInfoDOMapper;

    @Override
    public String getMenuTree(String userId) {
        List<SysUserResourceInfoDO> srcList = sysUserInfoDOMapper.getMenuTree(userId);
        if (CollectionUtils.isEmpty(srcList)) {
            return null;
        }
        List<SysUserResourceBO> treeList = new ArrayList<>();
        // 获取顶级菜单
        for (SysUserResourceInfoDO srcItem : srcList) {
            if (ResourceCodeEnum.C0.getCode().equals(srcItem.getParentCode())) {
                SysUserResourceBO parent = new SysUserResourceBO();
                BeanUtils.copyProperties(srcItem, parent);
                treeList.add(parent);
            }
        }

        for (SysUserResourceBO tree : treeList) {
            getMenuTree(srcList, tree);
        }
        return JsonUtils.objectToJson(treeList);
    }

    @Override
    public Set<String> getResourceSet(String userId) {
        return null;
    }

    /**
     * 获取菜单树
     *
     * @param srcList
     * @param tree
     * @author ex-wangmengnan
     * @description
     * @date 2018/2/5 15:06
     */
    private void getMenuTree(List<SysUserResourceInfoDO> srcList, SysUserResourceBO tree) {
        for (SysUserResourceInfoDO srcItem : srcList) {
            if (tree.getResCode().equals(srcItem.getParentCode())) {
                SysUserResourceBO child = new SysUserResourceBO();
                BeanUtils.copyProperties(srcItem, child);
                List<SysUserResourceBO> children = null;
                if (tree.getChildNodes() == null) {
                    children = new ArrayList<>();
                    tree.setChildNodes(children);
                } else {
                    children = tree.getChildNodes();
                }
                if (!ResourceTypeEnum.BUTTON.getCode().equals(srcItem.getResType())) {
                    children.add(child);
                }
            }
        }

        if (tree.getChildNodes() != null) {
            for (SysUserResourceBO item : tree.getChildNodes()) {
                getMenuTree(srcList, item);
            }
        }
    }
}
