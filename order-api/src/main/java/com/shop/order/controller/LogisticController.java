package com.shop.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.entity.order.Logistic;
import com.shop.order.service.LogisticService;
import com.shop.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.shop.utils.ShopCode.SHOP_4005_INSTALL_FAIL;

@RestController
@RequestMapping("/shop_order_api/logistic/")
public class LogisticController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogisticController.class);

    @Autowired
    private LogisticService logisticService;

    /**
     * 查询物流
     * @param order
     * @return
     */
    @GetMapping("/{order}")
    public Result queryByOrder(@PathVariable("order") String  order) {
        LOGGER.info("queryByOrder ", order);
        Logistic logistic = logisticService.getOne(new QueryWrapper<Logistic>()
                .eq("orders", order));
        return logistic != null ? Result.success(logistic) : Result.result(SHOP_4005_INSTALL_FAIL);
    }

}
