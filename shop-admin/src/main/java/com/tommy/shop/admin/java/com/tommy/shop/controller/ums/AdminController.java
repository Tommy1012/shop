package com.tommy.shop.admin.java.com.tommy.shop.controller.ums;

import com.tommy.shop.admin.java.com.tommy.shop.dto.UmsAdminParam;
import com.tommy.shop.admin.java.com.tommy.shop.service.ums.UmsAdminService;
import com.tommy.shop.common.result.CommonResult;
import com.tommy.shop.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台用户管理
 *
 * @author chengk
 * @date 2020/8/19
 */
@Controller
@Api(tags = "AdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class AdminController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminService adminService;

    /**
     * 后台用户注册
     *
     * @param umsAdminParam
     * @param result
     * @author chengk
     * @date 2020/8/20 11:11 上午
     */
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdminParam umsAdminParam, BindingResult result) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed("用户注册失败!");
        }
        return CommonResult.success(umsAdmin);
    }


}
