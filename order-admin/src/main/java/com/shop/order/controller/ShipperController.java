package com.shop.order.controller;

import com.shop.entity.order.Shipper;
import com.shop.order.service.ShipperService;
import com.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.shop.utils.ShopCode.SHOP_4004_NOTFOUND;

/**
 * @Author YKF
 * @date 2020/3/29下午1:02
 */
@RestController
@RequestMapping("/shop_order_admin/shipper/")
public class ShipperController {

    @Autowired
    private ShipperService shipperService;

    /**
     * 查询所有快递公司
     * @return
     */
    @GetMapping("/")
    public Result queryAll() {
        List<Shipper> list = shipperService.queryAll();
        return list == null || list.size() <= 0 ? Result.result(SHOP_4004_NOTFOUND) : Result.success(list);
    }
}
