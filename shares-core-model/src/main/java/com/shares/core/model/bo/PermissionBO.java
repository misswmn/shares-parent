package com.shares.core.model.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author ex-wangmengnan
 * @date 2017/8/31
 */
@Data
@Builder
public class PermissionBO {
    /**
     * 资源名
     */
    private String name;
    /**
     * 资源路径(@RequestMapping)
     */
    private List<String> url;
}