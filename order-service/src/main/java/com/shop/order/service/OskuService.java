package com.shop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.bean.order.AddOrderBean;
import com.shop.entity.order.Osku;

import java.util.List;

/**
 * 订单详细
 * @Author YKF
 * @date 2020/4/6上午10:57
 */
public interface OskuService extends IService<Osku> {

    /**
     * 详细订单存库
     * @param orderId
     * @param oskus
     * @return
     */
    boolean saveOskus(String orderId, List<AddOrderBean.Osku> oskus);

}
