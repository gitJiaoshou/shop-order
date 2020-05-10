package com.shop.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.bean.order.AddOrderBean;
import com.shop.bean.order.OrderRedisStatusEnum;
import com.shop.cache.order.service.OrderCache;
import com.shop.db.order.OrderMapper;
import com.shop.entity.order.Order;
import com.shop.order.service.OrderService;
import com.shop.utils.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author YKF
 * @date 2020/3/29下午1:53
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderCache orderCache;

    @Override
    public List<Order> queryByStatus(Integer status, Integer payStatus) {
        List<Order> list = baseMapper.selectList(new QueryWrapper<Order>()
                .ne("delete_status", StatusEnum.Del.getValue())
                .eq("pay_status", payStatus)
                .eq("status", status));
        return list;
    }

    @Override
    public List<Order> queryByStatusAndYgwId(String ygwId, Integer status) {
        List<Order> list = baseMapper.selectList(new QueryWrapper<Order>()
                .ne("delete_status", StatusEnum.Del.getValue())
                .eq("ygw_id", ygwId)
                .eq("status", status));
        return list;
    }

    @Override
    public boolean saveCache(String appKey, String ygwId, String id, OrderRedisStatusEnum orderRedisStatusEnum) {
        String key = String.format("%s:%s", appKey, id);
        boolean result = false;
        try {
            orderCache.saveOrderStatus(key, orderRedisStatusEnum.getValue());
            result = true;
        } catch (Exception e) {
            LOGGER.error("save order output kafka error", e);
        }
        return result;
    }

    @Override
    public boolean saveOneBySql(String appKey, Order order) {
        LOGGER.info("saveOneBySql saveOneBySql saveOneBySql appKey:{}", appKey);
        LOGGER.info("saveOneBySql saveOneBySql saveOneBySql order:{}", JSON.toJSONString(order));
        int insert = baseMapper.saveOneBySql(appKey, order);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equalsMoney(AddOrderBean addOrderBean) {
        boolean result = false;
        try {
            Float money = 0F;
            for (AddOrderBean.Osku oskus : addOrderBean.getOskus()) {
                money += oskus.getNewPrice() * oskus.getNumber();
            }
            result = money.equals(addOrderBean.getTotalPrice());
        } catch (Exception e) {
            LOGGER.error("equalsMoney error ", e);
        }
        return result;
    }
}
