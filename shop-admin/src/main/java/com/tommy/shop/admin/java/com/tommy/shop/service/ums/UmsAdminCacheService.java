package com.tommy.shop.admin.java.com.tommy.shop.service.ums;

import com.tommy.shop.model.UmsResource;

import java.util.List;

/**
 * 缓存service
 *
 * @author chengk
 * @date 2020/9/21
 */
public interface UmsAdminCacheService {

    /**
     * 获取缓存中后台用户资源列表
     *
     * @param id
     * @return
     * @author chengk
     * @date 2020/9/21 4:24 下午
     */
    List<UmsResource> getResourceList(Long id);

    /**
     * 设置后台后台用户资源列表
     *
     * @param adminId
     * @param resourceList
     * @author chengk
     * @date 2020/9/21 5:00 下午
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);
}
