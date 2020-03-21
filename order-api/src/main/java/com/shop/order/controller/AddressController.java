package com.shop.order.controller;

import com.alibaba.fastjson.JSON;
import com.shop.entity.order.Address;
import com.shop.order.service.AddressService;
import com.shop.utils.HeaderConstants;
import com.shop.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shop.utils.ShopCode.*;

/**
 * 地址管理API接口
 *
 * @Author YKF
 * @date 2020/3/18下午12:10
 */
@RestController
@RequestMapping("/shop_order_api/address/")
public class AddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    /**
     * 保存
     *
     * @param ygwId
     * @param address
     * @return
     */
    @PostMapping("/")
    public Result save(@RequestHeader(name = HeaderConstants.YGWID) String ygwId, @RequestBody Address address) {
        LOGGER.info("Address install bean:[{}]", JSON.toJSONString(address));
        if (!ygwId.equals(address.getYgwId())) {
            return Result.result(SHOP_43002_VERIFY_FAIL);
        }
        return addressService.save(address) ? Result.success() : Result.result(SHOP_4005_INSTALL_FAIL);
    }

    /**
     * 删除
     *
     * @param ygwId
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@RequestHeader(name = HeaderConstants.YGWID) String ygwId, @PathVariable("id") String id) {
        LOGGER.info("Address delete id:{}", id);
        boolean flat = flatUser(ygwId, id);
        if (!flat) {
            return Result.result(SHOP_43002_VERIFY_FAIL);
        }
        return addressService.deleteOne(id) ? Result.success() : Result.result(SHOP_4006_DELETE_FAIL);
    }

    /**
     * 根据用户id查询
     *
     * @param headYgwId
     * @param ygwId
     * @return
     */
    @GetMapping("/{ygwId}")
    public Result queryByUserId(@RequestHeader(name = HeaderConstants.YGWID) String headYgwId, @PathVariable("ygwId") String ygwId) {
        LOGGER.info("Address queryByUserId headYgwId:{} ygwId:{}", headYgwId, ygwId);
        if (!headYgwId.equals(ygwId)) {
            return Result.result(SHOP_43002_VERIFY_FAIL);
        }
        List<Address> list = addressService.queryByYgwId(ygwId);
        return list == null ? Result.result(SHOP_4004_NOTFOUND) : Result.success(list);
    }

    /**
     * 更新
     * @param address
     * @return
     */
    @PutMapping("/")
    public Result updateById(@RequestHeader(name = HeaderConstants.YGWID) String ygwId, @RequestBody Address address) {
        LOGGER.info("Address updateById bean:[{}]", JSON.toJSONString(address));
        boolean flat = flatUser(ygwId, address.getId());
        if (!flat) {
            return Result.result(SHOP_43002_VERIFY_FAIL);
        }
        return addressService.updateById(address) ? Result.success() : Result.result(SHOP_4007_UPDATE_FAIL);
    }

    /**
     * 校验
     *
     * @param ygwId 用户主键
     * @param id    地址主键
     * @return
     */
    private boolean flatUser(String ygwId, String id) {
        List<Address> addresses = addressService.queryByYgwId(ygwId);
        //验证是否是自己
        boolean flat = false;
        for (Address address : addresses) {
            if (address.getId().equals(id)) {
                flat = true;
                break;
            }
        }
        return flat;
    }
}
