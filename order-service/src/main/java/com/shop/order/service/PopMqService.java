package com.shop.order.service;

import com.shop.order.service.kafka.MySink;
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
public class PopMqService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PopMqService.class);

    @StreamListener(value = MySink.INPUT_ORDER)
    public void receive(String payload) {
        LOGGER.info("INPUT_ORDER received:" + payload);
    }

    @StreamListener(value = MySink.INPUT_ORDER)
    public void receive2(String payload) {
        LOGGER.info("INPUT_ORDER received:" + payload);
    }
}
