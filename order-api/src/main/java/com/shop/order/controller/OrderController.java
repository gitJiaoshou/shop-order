package com.shop.order.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.shop.bean.order.AddOrderBean;
import com.shop.bean.order.OrderRedisStatusEnum;
import com.shop.cache.order.service.OrderCache;
import com.shop.order.kafka.service.PushMqService;
import com.shop.order.service.OrderService;
import com.shop.utils.HeaderConstants;
import com.shop.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


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

    @Autowired
    private OrderCache orderCache;
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
        if (!orderService.equalsMoney(addOrderBean)) {
            return Result.result(SHOP_4000_REQUEST);
        }
        String id = IdWorker.getIdStr();
        addOrderBean.setId(id);
        String key = String.format("%s:%s:%s", appKey, addOrderBean.getYgwId(), id);
        addOrderBean.setRedisKey(key);
        pushMqService.pushGoodsMsg(appKey, addOrderBean);
        return orderService.saveCache(appKey, addOrderBean.getYgwId(), id, OrderRedisStatusEnum.START) ? Result.success(id) : Result.result(SHOP_4005_INSTALL_FAIL);
    }

    @GetMapping("/{order}")
    public Result queryOrderStatus(@PathVariable("order") String order) {

        String value = orderCache.getOrderStatus(order);
        if (StringUtils.isEmpty(value)) {
            return Result.result(SHOP_4004_NOTFOUND);
        }
        return Result.success(value);
    }
}
