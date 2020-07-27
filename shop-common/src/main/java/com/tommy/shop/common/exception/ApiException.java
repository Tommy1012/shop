package com.tommy.shop.common.exception;

import com.tommy.shop.common.result.IErrorCode;
import lombok.Getter;

/**
 * @author chengk
 * @date 2020/7/20
 */
public class ApiException extends RuntimeException {

    /**
     * 错误码
     */
    @Getter
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message,Throwable cause) {
        super(message,cause);
    }

}
