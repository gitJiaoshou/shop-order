package com.shop.order.controller;

import com.alibaba.fastjson.JSON;
import com.shop.bean.order.AddOrderBean;
import com.shop.bean.order.OrderRedisStatusEnum;
import com.shop.entity.order.Order;
import com.shop.order.kafka.service.MySink;
import com.shop.order.service.OrderService;
import com.shop.order.service.OskuService;
import com.shop.utils.OrderEnum;
import com.shop.utils.PayStatusEnum;
import com.shop.utils.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * 消费者
 * @Author YKF
 * @date 2020/3/29下午12:00
 */

@EnableBinding(MySink.class)
public class PopMqController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PopMqController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private OskuService oskuService;

    /**
     * 1. 订单存库
     * @param payload
     */
    @StreamListener(value = MySink.INPUT_ORDER)
    public void receive(String payload) {
        LOGGER.info("INPUT_ORDER received:" + payload);
        AddOrderBean data = JSON.parseObject(payload, AddOrderBean.class);
        Order order = Order
                .builder()
                .ygwId(data.getYgwId())
                .totalPrice(Float.valueOf(data.getTotalPrice().toString()))
                .payType(data.getPayType())
                .status(OrderEnum.UNPAID.getValue())
                .deleteStatus(StatusEnum.YES.getValue())
                .payStatus(PayStatusEnum.BUYER.getValue())
                .build();
        // 保存
        String orderId = orderService.saveOne(order);
        oskuService.saveOskus(orderId, data.getOskus());
        orderService.saveCache(data.getAppKey(), data.getYgwId(), data.getId(), OrderRedisStatusEnum.START);
    }

}
