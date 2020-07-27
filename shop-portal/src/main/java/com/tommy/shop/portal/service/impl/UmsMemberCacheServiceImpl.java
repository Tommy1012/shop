package com.tommy.shop.portal.service.impl;

import com.tommy.shop.model.UmsMember;
import com.tommy.shop.portal.service.UmsMemberCacheService;
import com.tommy.shop.security.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author chengk
 * @date 2020/7/14
 */
@Service
public class UmsMemberCacheServiceImpl implements UmsMemberCacheService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.key.member}")
    private String REDIS_KEY_MEMBER;

    @Value("${redis.key.authCode}")
    private String REDIS_KEY_AUTH_CODE;

    @Value("${redis.expire.authCode}")
    private Long REDIS_EXPIRE_AUTH_CODE;

    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;

    /**
     * 存储验证码
     * @param telephone
     * @param authCode
     */
    @Override
    public void setAuthCode(String telephone, String authCode) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + telephone;
        redisService.set(key,authCode,REDIS_EXPIRE_AUTH_CODE);
    }

    /**
     * 根据手机号码 获取 验证码
     *
     * @param telephone
     * @author chengk
     * @date 2020/7/20 3:41 下午
     */
    @Override
    public String getAuthCode(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + telephone;
        return (String)redisService.get(key);
    }

    /**
     * 获取会员用户缓存
     *
     * @param username
     * @author chengk
     * @date 2020/7/21 1:59 下午
     */
    @Override
    public UmsMember getMember(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + username;
        return (UmsMember) redisService.get(key);
    }

    /**
     * 设置会员用户缓存
     *
     * @param member
     * @author chengk
     * @date 2020/7/21 1:59 下午
     */
    @Override
    public void setMember(UmsMember member) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + member.getUsername();
        redisService.set(key, member, REDIS_EXPIRE);
    }


    /**
     * 删除缓存信息
     *
     * @param member
     * @author chengk
     * @date 2020/7/21 1:59 下午
     */
    @Override
    public void delMember(UmsMember member){
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + member.getUsername();
        redisService.delete(key);
    }
}
