package com.tommy.shop.portal.service;

import com.tommy.shop.model.UmsMember;

/**
 * 会员缓存处理
 *
 * @author chengk
 * @date 2020/7/14
 */
public interface UmsMemberCacheService {

    /**
     * 设置验证码
     * @param telephone
     * @param authCode
     */
    void setAuthCode(String telephone, String authCode);

    /**
     * 根据手机号码 获取 验证码
     *
     * @param telephone
     * @return
     * @author chengk
     * @date 2020/7/20 3:40 下午
     */
    String getAuthCode(String telephone);

    /**
     * 获取会员用户缓存
     *
     * @param username
     * @return
     * @author chengk
     * @date 2020/7/20 5:20 下午
     */
    UmsMember getMember(String username);

    /**
     * 设置会员用户缓存
     *
     * @param member
     * @author chengk
     * @date 2020/7/20 5:21 下午
     */
    void setMember(UmsMember member);

    /**
     * 删除缓存信息
     *
     * @param member
     * @author chengk
     * @date 2020/7/21 2:00 下午
     */
    void delMember(UmsMember member);
}
