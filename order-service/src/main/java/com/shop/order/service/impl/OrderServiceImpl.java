package com.shop.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.db.order.OrderMapper;
import com.shop.entity.order.Order;
import com.shop.order.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @Author YKF
 * @date 2020/3/29下午1:53
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
