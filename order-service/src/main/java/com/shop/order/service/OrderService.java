package com.shop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.bean.order.AddOrderBean;
import com.shop.bean.order.OrderRedisStatusEnum;
import com.shop.entity.order.Order;

/**
 * 订单
 * @Author YKF
 * @date 2020/3/29下午1:52
 */
public interface OrderService extends IService<Order> {

    /**
     * 保存一条记录
     *
     * @param appKey
     * @param ygwId
     * @param id
     * @param orderRedisStatusEnum
     * @return
     */
    boolean saveCache(String appKey, String ygwId, String id, OrderRedisStatusEnum orderRedisStatusEnum);

    /**
     * 保存db
     * @param order
     * @return          主键
     */
    String saveOne(Order order);

    /**
     * 检查总价是否一致
     * @param addOrderBean
     * @return
     */
    boolean equalsMoney(AddOrderBean addOrderBean);
}
