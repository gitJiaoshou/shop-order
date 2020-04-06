package com.shop.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.bean.order.AddOrderBean;
import com.shop.bean.order.OrderRedisStatusEnum;
import com.shop.cache.order.service.OrderCache;
import com.shop.db.order.OrderMapper;
import com.shop.entity.order.Order;
import com.shop.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean saveOne(String appKey, AddOrderBean addOrderBean) {

        boolean result = false;
        try {
            orderCache.saveOrderStatus(addOrderBean.getRedisKey(), OrderRedisStatusEnum.START.getValue());
            result = true;
        } catch (Exception e) {
            LOGGER.error("save order output kafka error", e);
        }
        return result;
    }

    @Override
    public boolean equalsMoney(AddOrderBean addOrderBean) {
        boolean result = false;
        try {
            Double money = addOrderBean
                    .getOskus()
                    .stream()
                    .mapToDouble(o -> {
                        return o.getNumber() * o.getNewPrice();
                    }).sum();

            result = money.equals(addOrderBean.getTotalPrice());
        } catch (Exception e) {
            LOGGER.error("equalsMoney error ", e);
        }
        return result;
    }
}
