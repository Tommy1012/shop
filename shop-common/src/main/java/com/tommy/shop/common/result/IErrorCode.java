package com.tommy.shop.common.result;

/**
 * 封装api的错误码
 *
 * @author chengk
 * @date 2020/7/14
 */
public interface IErrorCode {

    /**
     * 获取返回码(错误码)
     * @return long
     */
    long getCode();

    /**
     * 获取信息(错误信息)
     * @return
     */
    String getMessage();

}
