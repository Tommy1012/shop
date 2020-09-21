package com.tommy.shop.admin.java.com.tommy.shop.service.ums;


import com.tommy.shop.admin.java.com.tommy.shop.dto.UmsAdminParam;
import com.tommy.shop.admin.java.com.tommy.shop.dto.UpdateAdminPasswordParam;
import com.tommy.shop.model.UmsAdmin;
import com.tommy.shop.model.UmsPermission;
import com.tommy.shop.model.UmsResource;
import com.tommy.shop.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台管理员Service
 * Created by macro on 2018/4/26.
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户对于角色
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
