package com.tommy.shop.common.exception;

/**
 * 断言处理类，用于抛出各种API异常
 *
 * @author chengk
 * @date 2020/7/20
 */
public class Asserts {

    public static void fail(String message){throw new ApiException(message);}
}
