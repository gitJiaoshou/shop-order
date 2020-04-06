package com.shop.order.controller;

import com.shop.order.kafka.service.MySink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @StreamListener(value = MySink.INPUT_ORDER)
    public void receive(String payload) {
        LOGGER.info("INPUT_ORDER received:" + payload);
    }

}
