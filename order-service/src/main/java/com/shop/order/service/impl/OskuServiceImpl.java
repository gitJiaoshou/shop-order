package com.shop.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.bean.order.AddOrderBean;
import com.shop.db.order.OskuMapper;
import com.shop.entity.order.Osku;
import com.shop.order.service.OskuService;
import io.netty.handler.timeout.ReadTimeoutException;
import org.omg.CORBA.portable.IndirectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单详情
 * @Author YKF
 * @date 2020/4/6上午10:58
 */
@Service("oskuService")
public class OskuServiceImpl extends ServiceImpl<OskuMapper, Osku> implements OskuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OskuServiceImpl.class);

    @Override
    @Transactional
    public boolean saveOskusBySql(String appKey, String orderId, List<AddOrderBean.Osku> oskus) {
        try {

            oskus.forEach(t ->
                    baseMapper.saveOskusBySql(appKey, Osku
                            .builder()
                            .id(IdWorker.getIdStr())
                            .order(orderId)
                            .sku(t.getSku())
                            .newPrice(Float.valueOf(String.valueOf(t.getNewPrice())))
                            .price(Float.valueOf(String.valueOf(t.getPrice())))
                            .number(t.getNumber())
                            .build())
            );
        } catch (Exception e) {
            LOGGER.error("saveOskus error:", e);
            throw new RuntimeException("save saveOsks error" + e.getCause());
        }
        return false;
    }
}
