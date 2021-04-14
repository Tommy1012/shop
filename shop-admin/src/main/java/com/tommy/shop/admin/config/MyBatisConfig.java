package com.tommy.shop.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *  MyBatis相关配置
 *
 *  @author    chengk
 *  @date      2021/4/12 9:04 下午
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.tommy.shop.mapper","com.tommy.shop.admin.dao"})
public class MyBatisConfig {
}
