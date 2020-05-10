package com.shop.order.controller;

import com.shop.entity.order.Osku;
import com.shop.order.service.OskuService;
import com.shop.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.shop.utils.ShopCode.SHOP_4004_NOTFOUND;

/**
 * 订单详情
 * @author YKF
 * @date 2020/5/10下午12:03
 */
@RestController
@RequestMapping("/shop_order_api/osku/")
public class OskuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OskuController.class);

    @Autowired
    private OskuService oskuService;

    /**
     * 根据订单号查询
     * @param orderId
     * @return
     */
    @GetMapping("/{order}")
    public Result queryById(@PathVariable("order") String orderId) {
        List<Osku> list = oskuService.queryByOrder(orderId);
        return list == null || list.size() <= 0 ? Result.result(SHOP_4004_NOTFOUND) : Result.success(list);
    }
}
