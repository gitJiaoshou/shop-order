package com.shop.order.kafka.service;

/**
 * @Author YKF
 * @date 2020/3/29下午12:04
 */
public interface PushMqService {

    /**
     * 压栈
     *
     * @param appKey
     * @param msg
     */
    void pushGoodsMsg(String appKey, Object msg);
}
