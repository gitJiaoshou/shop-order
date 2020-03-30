package com.shop.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.db.order.ShipperMapper;
import com.shop.entity.order.Shipper;
import com.shop.order.service.ShipperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author YKF
 * @date 2020/3/29下午12:38
 */
@Service("shipperService")
public class ShipperServiceImpl extends ServiceImpl<ShipperMapper, Shipper> implements ShipperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipperServiceImpl.class);

    @Override
    public List<Shipper> queryAll() {
        return baseMapper.selectList(null);
    }
}
