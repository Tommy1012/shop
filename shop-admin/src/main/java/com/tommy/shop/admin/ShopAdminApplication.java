package com.tommy.shop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后台启动类
 *
 * @author chengk
 * @date 2020/8/19 4:57 下午
 */
@SpringBootApplication(scanBasePackages = "com.tommy.shop")
public class ShopAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopAdminApplication.class, args);
    }

}
