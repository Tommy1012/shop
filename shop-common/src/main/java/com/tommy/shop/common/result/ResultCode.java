package com.tommy.shop.common.result;

/**
 * 返回响应
 *
 * @author chengk
 * @date 2020/7/14
 */
public enum ResultCode implements IErrorCode {

    /**
     * 成功状态码
     */
    SUCCESS(200, "操作成功"),

    /**
     * 失败状态码
     */
    FAILED(500, "操作失败"),

    /**
     * 参数校验失败，拒绝访问
     */
    VALIDATE_FAILED(404, "参数检验失败"),

    /**
     * 未授权请求没有进行身份验证或验证未通过
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    /**
     * 没有相关权限，禁止访问服务器拒绝此请求
     */
    FORBIDDEN(403, "没有相关权限"),
    ;

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
