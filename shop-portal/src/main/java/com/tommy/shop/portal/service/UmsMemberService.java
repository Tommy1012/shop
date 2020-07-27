package com.tommy.shop.portal.service;

import com.tommy.shop.model.UmsMember;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员登录相关 service
 *
 * @author chengk
 * @date 2020/7/13
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     * @param telephone
     * @return 验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 会员注册
     *
     * @param userName
     * @param password
     * @param telephone
     * @param authCode
     * @return boolean 成功或失败
     * @author chengk
     * @date 2020/7/20 3:04 下午
     */
    @Transactional(rollbackFor = Exception.class)
    boolean register(String userName, String password, String telephone, String authCode);

    /**
     * 获取用户信息
     *
     * @param username
     * @author chengk
     * @date 2020/7/20 5:13 下午
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 根据用户名获取会员
     *
     * @param username
     * @return
     * @author chengk
     * @date 2020/7/20 5:19 下午
     */
    UmsMember getMember(String username);

    /**
     * 修改会员密码
     *
     * @param newPassword
     * @param telephone
     * @param authCode
     * @return
     * @author chengk
     * @date 2020/7/21 12:18 下午
     */
    @Transactional(rollbackFor = Exception.class)
    boolean updatePassword(String newPassword, String telephone, String authCode);

    /**
     * 会员登录
     *
     * @param username
     * @param password
     * @return token
     * @author chengk
     * @date 2020/7/21 2:21 下午
     */
    String login(String username, String password);

    /**
     * 获取当前登录会员
     *
     * @param
     * @author chengk
     * @date 2020/7/22 11:31 下午
     */
    UmsMember getCurrentMember();

    /**
     * 刷新token
     *
     * @param token
     * @author chengk
     * @date 2020/7/22 11:37 下午
     */
    String refreshToken(String token);
}
