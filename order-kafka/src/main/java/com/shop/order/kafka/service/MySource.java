package com.shop.order.kafka.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 提供者
 * @Author YKF
 * @date 2020/3/29上午11:54
 */
public interface MySource {

    /**
     * 商品
     */
    String OUTPUT_GOODS = "output_goods";

    /**
     * 商品
     * @return
     */
    @Output(MySource.OUTPUT_GOODS)
    MessageChannel outputGoods();
}
