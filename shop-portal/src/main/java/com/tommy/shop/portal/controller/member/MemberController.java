package com.tommy.shop.portal.controller.member;

import com.google.common.collect.Maps;
import com.tommy.shop.common.result.CommonResult;
import com.tommy.shop.model.UmsMember;
import com.tommy.shop.portal.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员管理 -> 登录相关
 *
 * @author chengk
 * @date 2020/7/13
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso/")
public class MemberController {

    @Autowired
    private UmsMemberService memberService;

    @Value("jwt.tokenHead")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    /**
     * 获取验证码
     *
     * @param telephone
     * @author chengk
     * @date 2020/7/13 7:28 下午
     */
    @ApiOperation("获取验证码")
    @RequestMapping(value = "getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        String authCode = memberService.generateAuthCode(telephone);
        return CommonResult.success(authCode,"获取验证码成功");
    }

    /**
     * 注册
     *
     * @param
     * @author chengk
     * @date 2020/7/20 2:05 下午
     */
    @ApiOperation("会员注册")
    @ResponseBody
    @PostMapping("register")
    public CommonResult register(@RequestParam String username,@RequestParam String password,
                         @RequestParam String telephone,@RequestParam String authCode){
        return CommonResult.success(memberService.register(username,password,telephone,authCode),"注册成功！");
    }

    /**
     * 会员修改密码
     *
     * @param newPassword
     * @param telephone
     * @param authCode
     * @author chengk
     * @date 2020/7/21 12:01 下午
     */
    @ApiOperation("会员修改密码")
    @ResponseBody
    @PostMapping("updatePassword")
    public CommonResult updatePassword(@RequestParam String newPassword,@RequestParam String telephone,
                                       @RequestParam String authCode){
        return CommonResult.success(memberService.updatePassword(newPassword,telephone,authCode));
    }

    /**
     * 登录
     * note：SpringSecurity+JWT来实现登录认证，逻辑抽取到了mall-security模块
     *
     * @param username
     * @param password
     * @author chengk
     * @date 2020/7/22 11:35 下午
     */
    @ApiOperation("登录")
    @ResponseBody
    @PostMapping("login")
    public CommonResult login(@RequestParam String username,@RequestParam String password){
        String token = memberService.login(username,password);
        if(StringUtils.isBlank(token)){
            return CommonResult.validateFailed("用户名或密码错误！");
        }
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("token",token);
        resultMap.put("tokenHead",tokenHead);
        return CommonResult.success(resultMap);
    }

    /**
     * 获取会员信息
     *
     * @param principal
     * @author chengk
     * @date 2020/7/22 11:35 下午
     */
    @ApiOperation("获取会员信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult info(Principal principal){
        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        UmsMember member = memberService.getCurrentMember();
        return CommonResult.success(member);
    }

    /**
     * 刷新token
     *
     * @param request
     * @author chengk
     * @date 2020/7/22 11:36 下午
     */
    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = memberService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }


}
