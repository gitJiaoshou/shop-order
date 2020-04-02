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
     * 订单
     */
    String OUTPUT_ORDER = "output_order";

    /**
     * 商品
     */
    String OUTPUT_GOODS = "output_goods";

    /**
     * 订单
     * @return
     */
    @Output(MySource.OUTPUT_ORDER)
    MessageChannel outputOrder();

    /**
     * 商品
     * @return
     */
    @Output(MySource.OUTPUT_GOODS)
    MessageChannel outputGoods();
}
