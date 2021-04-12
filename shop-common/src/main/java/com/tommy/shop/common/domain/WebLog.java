package com.tommy.shop.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 *  日志记录实体类
 * 
 *  @author    chengk
 *  @date      2021/4/12 2:01 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebLog {

    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * URI
     */
    private String uri;

    /**
     * 请求url
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 返回结果
     */
    private Object result;
}
