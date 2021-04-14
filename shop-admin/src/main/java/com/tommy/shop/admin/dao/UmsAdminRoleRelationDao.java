package com.tommy.shop.admin.dao;

import com.tommy.shop.model.UmsAdminRoleRelation;
import com.tommy.shop.model.UmsResource;
import com.tommy.shop.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户-角色关联表
 *
 * @author chengk
 * @date 2020/9/21
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取资源相关用户ID列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);

}
