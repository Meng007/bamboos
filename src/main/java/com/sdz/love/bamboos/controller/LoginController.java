package com.sdz.love.bamboos.controller;

import com.sdz.love.bamboos.commons.BaseController;
import com.sdz.love.bamboos.commons.ResponseResult;
import com.sdz.love.bamboos.commons.config.service.LoginBody;
import com.sdz.love.bamboos.commons.config.service.RedisService;
import com.sdz.love.bamboos.commons.config.service.TokenService;
import com.sdz.love.bamboos.commons.config.service.impl.LoginServiceImpl;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.service.TbMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 登录
 * @author 13557
 */
@RestController
public class LoginController extends BaseController {


    @Autowired
    private RedisService redisService;

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TbMemberService tbMemberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody @Valid LoginBody loginBody){

        if (StringUtils.isEmpty(loginBody.getType())){
            return null;
        }
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(),loginBody.getUuid(),loginBody.getCode(), loginBody.getType());
        Map<String,Object> re = new HashMap<>(1);
        re.put("token",token);
        return ResponseResult.success(re);

    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping("/register")
    public ResponseResult register(@RequestBody TbMember tbMember){
        tbMember.setCreateTime(new Date());
        tbMember.setPassword(passwordEncoder.encode(tbMember.getPassword()));
        tbMember.setIsDelete(1);
        tbMember.setStatus("1");
        tbMember.setIsAdmin(0);
        tbMember.setUpdateTime(new Date());
        boolean is = tbMemberService.save(tbMember);
        return super.result(is);
    }


}
