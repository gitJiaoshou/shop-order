package com.shop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.entity.order.Address;

import java.util.List;

/**
 * 地址
 * @Author YKF
 * @date 2020/3/18下午10:16
 */
public interface AddressService extends IService<Address> {

    /**
     * 保存一条记录
     * @param address
     * @return
     */
    boolean saveOne(Address address);

    /**
     * 修改
     * @param address
     * @return
     */
    boolean updateOneById(Address address);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean deleteOne(String id);

    /**
     * 根据用户查询地址
     * @param ygwId
     * @return
     */
    List<Address> queryByYgwId(String ygwId);

}
