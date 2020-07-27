package com.tommy.shop.portal.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tommy.shop.common.enums.umsMember.MemberLevelDefaultStatusEnum;
import com.tommy.shop.common.enums.umsMember.MemberStatusEnum;
import com.tommy.shop.common.exception.Asserts;
import com.tommy.shop.mapper.UmsMemberLevelMapper;
import com.tommy.shop.mapper.UmsMemberMapper;
import com.tommy.shop.model.UmsMember;
import com.tommy.shop.model.UmsMemberExample;
import com.tommy.shop.model.UmsMemberLevel;
import com.tommy.shop.model.UmsMemberLevelExample;
import com.tommy.shop.portal.domain.MemberDetails;
import com.tommy.shop.portal.service.UmsMemberCacheService;
import com.tommy.shop.portal.service.UmsMemberService;
import com.tommy.shop.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 会员登录相关 service 实现
 *
 * @author chengk
 * @date 2020/7/13
 */
@Slf4j
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private UmsMemberCacheService memberCacheService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UmsMemberMapper memberMapper;

    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;

    /**
     * 获取验证码
     * -> 存入redis
     *
     * @param telephone
     */
    @Override
    public String generateAuthCode(String telephone) {
        String authCode = String.format("%06d",new Random().nextInt(10000));
        memberCacheService.setAuthCode(telephone,authCode);
        return authCode;
    }

    /**
     * 会员注册
     *
     * @param username
     * @param password
     *      * @param telephone
     *      * @param authCode
     * @author chengk
     * @date 2020/7/20 3:09 下午
     */
    @Override
    public boolean register(String username, String password, String telephone, String authCode) {
        //效验验证码
        if(!verifyAuthCode(authCode,telephone)){
            Asserts.fail("验证码错误!");
        }
        List<UmsMember> umsMemberList = getUmsMembers(username, telephone);
        if(CollectionUtils.isNotEmpty(umsMemberList)){
            Asserts.fail("用户已存在!");
        }
        //新增会员
        addNewMember(username, password, telephone);
        return true;
    }

    /**
     * 查询用户
     *
     * @param username
     * @param telephone
     * @author chengk
     * @date 2020/7/20 10:19 下午
     */
    private List<UmsMember> getUmsMembers(String username, String telephone) {
        //是否有该用户,手机号码与用户名唯一
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("username",StringUtils.isBlank(username) ? null : username);
        conditionMap.put("telephone",StringUtils.isBlank(telephone) ? null : telephone);
        List<UmsMember> umsMemberList = memberMapper.selectListByMap(conditionMap);
        return CollectionUtils.isEmpty(umsMemberList) ? Lists.newArrayList() : umsMemberList;
    }

    /**
     * 新增新会员
     *
     * @param userName
     * @param password
     * @param telephone
     * @author chengk
     * @date 2020/7/20 4:28 下午
     */
    private UmsMember addNewMember(String userName, String password, String telephone) {
        UmsMember member = new UmsMember();
        member.setUsername(userName);
        member.setPassword(passwordEncoder.encode(password));
        member.setPhone(telephone);
        member.setCreateTime(new Date());
        member.setStatus(MemberStatusEnum.USING.getValue());
        //获取会员默认等级并设置
        UmsMemberLevelExample memberLevelExample = new UmsMemberLevelExample();
        memberLevelExample.createCriteria().andDefaultStatusEqualTo(MemberLevelDefaultStatusEnum.YES.getValue());
        List<UmsMemberLevel> umsMemberLevelList = memberLevelMapper.selectByExample(memberLevelExample);
        if(CollectionUtils.isEmpty(umsMemberLevelList)){
            member.setMemberLevelId(umsMemberLevelList.get(0).getId());
        }
        memberMapper.insert(member);
        member.setPassword(null);
        return member;
    }

    /**
     * 效验验证码
     *
     * @param authCode
     * @author chengk
     * @date 2020/7/20 3:22 下午
     */
    private boolean verifyAuthCode(String authCode,String telephone) {
        if(StringUtils.isAnyBlank(authCode,telephone)){
            return false;
        }
        String realAuthCode = memberCacheService.getAuthCode(telephone);
        return authCode.equals(realAuthCode);
    }

    /**
     * 根据用户名获取会员详细信息
     *
     * @param username
     * @author chengk
     * @date 2020/7/20 5:19 下午
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember member = getMember(username);
        if(member!=null){
            return new MemberDetails(member);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 根据用户名获取会员信息
     *
     * @param username
     * @author chengk
     * @date 2020/7/20 5:19 下午
     */
    @Override
    public UmsMember getMember(String username) {
        UmsMember member = memberCacheService.getMember(username);
        if(member!=null) {
            return member;
        }
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!org.springframework.util.CollectionUtils.isEmpty(memberList)) {
            member = memberList.get(0);
            memberCacheService.setMember(member);
            return member;
        }
        return null;
    }

    /**
     * 修改会员密码
     *
     * @param newPassword
     * @param telephone
     * @param authCode
     * @author chengk
     * @date 2020/7/21 12:20 下午
     */
    @Override
    public boolean updatePassword(String newPassword, String telephone, String authCode) {
        //效验会员
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andPhoneEqualTo(telephone);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(memberList)){
            Asserts.fail("该号码绑定用户不存在!");
        }
        UmsMember umsMemberExist = memberList.get(0);
        umsMemberExist.setPassword(passwordEncoder.encode(newPassword));
        memberMapper.updateByPrimaryKey(umsMemberExist);
        memberCacheService.delMember(umsMemberExist);
        return true;
    }

    /**
     * 会员登录
     *
     * @param username
     * @param password
     * @author chengk
     * @date 2020/7/21 5:26 下午
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        try{
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确!");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        }catch (AuthenticationException e){
            log.info("登录异常:{}",e.getMessage());
        }
        return token;
    }

    /**
     * 获取当前登录会员信息
     *
     * @param
     * @author chengk
     * @date 2020/7/22 11:31 下午
     */
    @Override
    public UmsMember getCurrentMember() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
        return memberDetails.getUmsMember();
    }

    /**
     * 刷新token
     *
     * @param token
     * @author chengk
     * @date 2020/7/22 11:38 下午
     */
    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }
}
