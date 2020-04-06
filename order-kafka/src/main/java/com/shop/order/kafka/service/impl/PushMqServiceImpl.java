package com.shop.order.kafka.service.impl;

import com.shop.order.kafka.service.MySource;
import com.shop.order.kafka.service.PushMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @Author YKF
 * @date 2020/3/29下午12:06
 */
@Service("pushMqService")
@EnableBinding(MySource.class)
public class PushMqServiceImpl implements PushMqService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushMqServiceImpl.class);
    public static final String APP_KEY = "AppKey";

    @Autowired
    private MySource mySource;

    @Override
    public void pushGoodsMsg(String appKey, Object msg) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("pushMsg =  {}", msg);
        }
        mySource.outputGoods()
                .send(
                        MessageBuilder.withPayload(msg)
                        .setHeader(APP_KEY, appKey)
                        .build()
                );
    }
}
