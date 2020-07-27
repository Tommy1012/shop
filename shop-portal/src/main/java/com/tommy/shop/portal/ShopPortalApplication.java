package com.tommy.shop.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ShopPortal项目启动类
 *
 * @author chengk
 * @date 2020/7/15 4:12 下午
 */
@SpringBootApplication(scanBasePackages = "com.tommy.shop")
public class ShopPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopPortalApplication.class, args);
    }

}
