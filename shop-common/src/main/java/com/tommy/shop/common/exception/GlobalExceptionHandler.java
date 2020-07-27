package com.tommy.shop.common.exception;

import cn.hutool.json.JSONUtil;
import com.tommy.shop.common.result.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author chengk
 * @date 2020/7/20
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理，一般情况下，springBoot异常处理的手段有
     *   -> 1.局部异常处理 @Controller + @ExceptionHandler
     *      2.全局异常处理 @ControllerAdvice + @ExceptionHandler
     *
     * @param e
     * @author chengk
     * @date 2020/7/21 9:29 上午
     */
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public static CommonResult handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        System.out.println(JSONUtil.parse(CommonResult.failed(e.getMessage())));
        return CommonResult.failed(e.getMessage());
    }
}
