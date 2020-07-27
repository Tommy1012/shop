package com.tommy.shop.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis 配置
 *
 * @author chengk
 * @date 2020/7/20
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.tommy.shop.mapper","com.tommy.shop.portal.dao"})
public class MyBatisConfig {
}
