package com.sdz.love.bamboos.commons.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.sdz.love.bamboos.commons.config.service.LoginUser;
import com.sdz.love.bamboos.controller.UserStatus;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.exception.BusinessException;
import com.sdz.love.bamboos.service.TbMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class MemberDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TbMemberService tbMemberService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LambdaQueryWrapper<TbMember> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TbMember::getUsername,s);
        TbMember member = tbMemberService.getOne(lambdaQueryWrapper);
        System.out.println("会员登录");
        if (Objects.isNull(member)){
            throw new BusinessException("登录用户："+ s+"不存在",502);
        }
        else if(UserStatus.DISABLE.getCode().equals(member.getStatus())){
            log.info("登录用户：{} 已被停用.", s);
            throw new BusinessException("对不起，您的账号：" + s + " 已停用",520);
        }
        else if (UserStatus.DELETED.getCode().equals(member.getIsDelete())){
            log.info("登录用户：{} 已被删除.", s);
            throw new BusinessException("对不起，您的账号：" + s + " 已删除",1025);
        }
        return createLoginUser(member);
    }

    private UserDetails createLoginUser(TbMember member) {

        return new LoginUser(member,null);
    }
}
