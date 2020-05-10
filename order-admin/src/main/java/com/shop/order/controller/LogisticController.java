package com.shop.order.controller;

import com.alibaba.fastjson.JSON;
import com.shop.entity.order.Logistic;
import com.shop.entity.order.Order;
import com.shop.order.service.LogisticService;
import com.shop.order.service.OrderService;
import com.shop.utils.LogisticEnum;
import com.shop.utils.OrderEnum;
import com.shop.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.shop.utils.ShopCode.SHOP_4005_INSTALL_FAIL;

@RestController
@RequestMapping("/shop_order_admin/logistic/")
public class LogisticController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogisticController.class);

    @Autowired
    private LogisticService logisticService;
    @Autowired
    private OrderService orderService;
    /**
     * 保存订单
     * @param logistic
     * @return
     */
    @PostMapping("/")
    public Result saveOne(@RequestBody Logistic logistic) {
        LOGGER.info("logistic install bean:[{}]", JSON.toJSONString(logistic));
        logistic.setStatus(LogisticEnum.NOTHING.getValue());
        boolean result = logisticService.save(logistic);
        if (result) {
            Order order = new Order();
            order.setId(logistic.getOrders());
            order.setStatus(OrderEnum.SEND.getValue());
            orderService.updateById(order);
        }
        return result ? Result.success() : Result.result(SHOP_4005_INSTALL_FAIL);
    }

}
