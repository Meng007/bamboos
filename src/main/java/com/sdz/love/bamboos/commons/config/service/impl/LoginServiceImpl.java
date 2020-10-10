package com.sdz.love.bamboos.commons.config.service.impl;

import com.sdz.love.bamboos.commons.config.service.LoginUser;
import com.sdz.love.bamboos.commons.config.service.RedisService;
import com.sdz.love.bamboos.commons.config.service.TokenService;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 13557
 */
@Component
public class LoginServiceImpl {

    private final String ADMIN = "admin";
    private final String STUDENT = "student";
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisService redisService;



    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid,String type)
    {
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                throw new BusinessException("密码错误",520);
            }
            else
            {
                throw new BusinessException("账号错误",520);
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        TbMember user = loginUser.getUser();
        System.out.println("用户类型："+type);
        if (ADMIN.equals(type)){
            if (user.getIsAdmin()!=1){
                throw new BusinessException("用户不存在",502);
            }
        }
        if (STUDENT.equals(type)){
            if (user.getIsAdmin() !=0){
                throw new BusinessException("用户不存在",502);
            }
        }
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
