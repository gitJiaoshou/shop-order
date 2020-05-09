package com.shop.order.controller;

import com.shop.entity.order.Order;
import com.shop.order.service.OrderService;
import com.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shop.utils.ShopCode.SHOP_4004_NOTFOUND;

@RestController
@RequestMapping("/shop_order_admin/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据状态查询
     *
     * @return
     */
    @GetMapping("/query")
    public Result queryByStatus(@RequestParam Integer status,@RequestParam Integer payStatus) {
        List<Order> list = orderService.queryByStatus(status, payStatus);
        return list == null || list.size() <= 0 ? Result.result(SHOP_4004_NOTFOUND) : Result.success(list);
    }
}
