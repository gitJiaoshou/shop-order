package com.shop.order.controller;

import com.alibaba.fastjson.JSON;
import com.shop.bean.order.AddOrderBean;
import com.shop.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YKF
 * @date 2020/3/29下午1:56
 */
@RestController
@RequestMapping("/shop_order_api/order/")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    /**
     * 新增订单
     * @param addOrderBean
     * @return
     */
    @PostMapping("/")
    public Result addOrder(@RequestBody AddOrderBean addOrderBean) {
        LOGGER.info("add order Post bean:[{}]", JSON.toJSONString(addOrderBean));

        return null;
    }
}
