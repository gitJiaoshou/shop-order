package com.shop.order.service;

/**
 * @Author YKF
 * @date 2020/3/29下午12:04
 */
public interface PushMqService {

    /**
     * 压栈
     * @param msg
     */
    public void pushOrderMsg(Object msg);

    /**
     * 压栈
     * @param msg
     */
    public void pushGoodsMsg(Object msg);
}
