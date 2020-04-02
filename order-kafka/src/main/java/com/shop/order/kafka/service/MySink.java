package com.shop.order.kafka.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 消费者
 * @Author YKF
 * @date 2020/3/28下午9:54
 */
public interface MySink {

    /**
     * 订单
     */
    String INPUT_ORDER = "input_order";

    /**
     * 商品
     */
    String INPUT_GOODS = "input_goods";

    /**
     * 订单
     * @return
     */
    @Input(MySink.INPUT_ORDER)
    SubscribableChannel inputOrder();

    /**
     * 商品
     * @return
     */
    @Input(MySink.INPUT_GOODS)
    SubscribableChannel inputGoods();

}
