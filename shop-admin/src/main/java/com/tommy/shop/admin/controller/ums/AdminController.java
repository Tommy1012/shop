package com.tommy.shop.admin.controller.ums;

import com.google.common.collect.Maps;
import com.tommy.shop.admin.dto.UmsAdminLoginParam;
import com.tommy.shop.admin.dto.UmsAdminParam;
import com.tommy.shop.admin.service.ums.UmsAdminService;
import com.tommy.shop.common.result.CommonResult;
import com.tommy.shop.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * @author chengk
     * @date 2020/8/20 11:11 上午
     */
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed("用户注册失败!");
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "用户登录并返回token")
    @PostMapping(value = "/login")
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam){
        String token = adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
        if(StringUtils.isEmpty(token)){
            return CommonResult.validateFailed("用户名或者密码错误");
        }
        Map<String,String> tokenMap = Maps.newHashMap();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHeader);
        return CommonResult.success(tokenMap);
    }


}
