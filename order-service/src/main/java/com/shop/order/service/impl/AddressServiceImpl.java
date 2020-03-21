package com.shop.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.db.order.AddressMapper;
import com.shop.entity.order.Address;
import com.shop.order.service.AddressService;
import com.shop.utils.StatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author YKF
 * @date 2020/3/18下午10:18
 */
@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    public boolean saveOne(Address address) {
        int insert = baseMapper.insert(address);
        if (insert <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateOneById(Address address) {
        int update = baseMapper.updateById(address);
        if (update < 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteOne(String id) {
        Address address = Address.
                builder()
                .id(id)
                .status(StatusEnum.Del.getValue())
                .build();
        return updateOneById(address);
    }

    @Override
    public List<Address> queryByYgwId(String ygwId) {
        return baseMapper.selectList(new QueryWrapper<Address>()
                .eq("ygw_id", ygwId)
                .eq("status", StatusEnum.YES.getValue()));
    }
}
