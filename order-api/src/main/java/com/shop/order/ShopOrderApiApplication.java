package com.shop.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author YKF
 * @date 2020/3/18上午11:41
 */
@SpringBootApplication
@MapperScan({"com.shop.db.order"})
@ComponentScan(basePackages = {"com.shop.cache.*","com.shop.order"})
@EnableAsync
public class ShopOrderApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopOrderApiApplication.class, args);
    }
}
