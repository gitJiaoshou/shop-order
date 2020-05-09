package com.shop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.bean.order.AddOrderBean;
import com.shop.bean.order.OrderRedisStatusEnum;
import com.shop.entity.order.Order;

import java.util.List;

/**
 * 订单
 * @Author YKF
 * @date 2020/3/29下午1:52
 */
public interface OrderService extends IService<Order> {

    /**
     * 根据状态查询
     * @param status
     * @return
     */
    List<Order> queryByStatus(Integer status,Integer payStatus);
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
     *
     * @param appKey
     * @param order
     * @return 主键
     */
    boolean saveOneBySql(String appKey, Order order);

    /**
     * 检查总价是否一致
     * @param addOrderBean
     * @return
     */
    boolean equalsMoney(AddOrderBean addOrderBean);
}
