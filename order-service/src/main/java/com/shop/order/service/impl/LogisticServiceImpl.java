package com.shop.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.db.order.LogisticMapper;
import com.shop.entity.order.Logistic;
import com.shop.order.service.LogisticService;
import org.springframework.stereotype.Service;

/**
 * @author YKF
 * @date 2020/5/9下午8:34
 */
@Service("logisticService")
public class LogisticServiceImpl extends ServiceImpl<LogisticMapper, Logistic> implements LogisticService {
}
