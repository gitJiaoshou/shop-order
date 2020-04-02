package com.shop.order.controller;

import com.alibaba.fastjson.JSON;
import com.shop.bean.order.AddOrderBean;
import com.shop.entity.order.Osku;
import com.shop.order.service.PushMqService;
import com.shop.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;

import static com.shop.utils.ShopCode.SHOP_2002_LOAD;
import static com.shop.utils.ShopCode.SHOP_4000_REQUEST;

/**
 * @Author YKF
 * @date 2020/3/29下午1:56
 */
@RestController
@RequestMapping("/shop_order_api/order/")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private PushMqService pushMqService;

    /**
     * 新增订单
     * @param addOrderBean
     * @return
     */
    @PostMapping("/")
    public Result addOrder(@RequestBody AddOrderBean addOrderBean) {
        LOGGER.info("add order Post bean:[{}]", JSON.toJSONString(addOrderBean));
        //统计总价
        Double money = addOrderBean
                .getOskus()
                .stream()
                .mapToDouble(o -> {
                    return o.getNumber() * o.getNewPrice();
                }).sum();

        if (money.equals(addOrderBean.getTotalPrice())) {
            return Result.result(SHOP_4000_REQUEST);
        }
        //TODO 在这里存一个redis的状态让客户端一直查询，类似于二维码登录
        pushMqService.pushGoodsMsg(addOrderBean);
        return Result.result(SHOP_2002_LOAD);
    }

}
