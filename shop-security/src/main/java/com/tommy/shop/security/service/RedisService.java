package com.tommy.shop.security.service;

/**
 * redis操作 service
 *
 * @author chengk
 * @date 2020/7/15
 */
public interface RedisService {

    /**
     * 保存属性
     */
    void set(String key, Object value, long time);

    /**
     * 保存属性
     */
    void set(String key, Object value);

    /**
     * 获取数据
     *
     * @param key
     * @return redis内容
     * @author chengk
     * @date 2020/7/20 3:45 下午
     */
    Object get(String key);

    /**
     * 删除
     *
     * @param key
     * @author chengk
     * @date 2020/7/20 3:47 下午
     */
    void delete(String key);
}
