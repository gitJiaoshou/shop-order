package com.shop.order.controller;

import com.alibaba.fastjson.JSON;
import com.shop.bean.order.AddOrderBean;
import com.shop.order.kafka.service.PushMqService;
import com.shop.order.service.OrderService;
import com.shop.utils.HeaderConstants;
import com.shop.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

import static com.shop.utils.ShopCode.*;

/**
 * @Author YKF
 * @date 2020/3/29下午1:56
 */
@RestController
@RequestMapping("/shop_order_api/order/")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private PushMqService pushMqService;
    /**
     * 新增订单
     *
     * @param appKey
     * @param addOrderBean
     * @return
     */
    @PostMapping("/")
    public Result addOrder(
            @RequestHeader(value = HeaderConstants.APP_KEY) String appKey,
            @RequestBody AddOrderBean addOrderBean) {
        LOGGER.info("add order Post bean:[{}]", JSON.toJSONString(addOrderBean));
        //统计总价
        if (orderService.equalsMoney(addOrderBean)) {
            return Result.result(SHOP_4000_REQUEST);
        }
        String key = String.format("%s:%s:%s", appKey, addOrderBean.getYgwId(), UUID.randomUUID().toString());
        addOrderBean.setRedisKey(key);
        pushMqService.pushGoodsMsg(appKey, addOrderBean);
        return orderService.saveOne(appKey, addOrderBean) ? Result.success(key) : Result.result(SHOP_4005_INSTALL_FAIL);
    }

}
