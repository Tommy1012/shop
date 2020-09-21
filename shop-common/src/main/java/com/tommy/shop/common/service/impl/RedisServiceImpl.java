package com.tommy.shop.common.service.impl;

import com.tommy.shop.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * redis操作 service实现类
 *
 * @author chengk
 * @date 2020/7/15
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 存入redis并且设置超时时间
     *
     * @param key
     * @param value
     * @param time
     */
    @Override
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    /**
     * 存入redis并且设置超时时间
     *
     * @param key
     * @param value
     */
    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 查
     *
     * @param key
     * @author chengk
     * @date 2020/7/20 3:56 下午
     */
    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删
     *
     * @param key
     * @author chengk
     * @date 2020/7/20 3:56 下午
     */
    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
