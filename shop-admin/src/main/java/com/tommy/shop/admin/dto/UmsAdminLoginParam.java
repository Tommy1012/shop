package com.tommy.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 登录入参
 *
 * @author chengk
 * @date 2020/9/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminLoginParam {

    @ApiModelProperty(value = "登录用户名",required = true)
    @NotBlank(message = "登录账号不能为空！")
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    @NotBlank(message = "密码不能为空！")
    private String password;
}
