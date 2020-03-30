package com.shop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.entity.order.Shipper;

import java.util.List;

/**
 * 快递字典
 * @Author YKF
 * @date 2020/3/29下午12:37
 */
public interface ShipperService extends IService<Shipper> {

    /**
     * 查询所有快递
     * @return
     */
    public List<Shipper> queryAll();
}
