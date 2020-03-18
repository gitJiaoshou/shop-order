package com.shop.order.execption;

import com.shop.exception.NamiException;
import com.shop.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 * @Author YKF
 * @date 2020/3/18上午11:50
 */
@RestControllerAdvice
public class ShopExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopExceptionHandler.class);

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(NamiException.class)
    public Result handleException(NamiException e) {
        return Result.result(e.getCode(), e.getReason(), null);
    }

    /**
     * 500异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result  handleException(Exception e) {
        LOGGER.error("server error:",e);
        return Result.error("server err,");
    }
}
