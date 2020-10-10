package com.sdz.love.bamboos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdz.love.bamboos.commons.BaseController;
import com.sdz.love.bamboos.commons.ResponseResult;
import com.sdz.love.bamboos.commons.config.service.LoginUser;
import com.sdz.love.bamboos.commons.config.service.TokenService;
import com.sdz.love.bamboos.commons.constant.ResponseConstants;
import com.sdz.love.bamboos.commons.dto.PageDto;
import com.sdz.love.bamboos.commons.dto.PasswordDto;
import com.sdz.love.bamboos.commons.util.ServletUtils;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.service.TbMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class MemberController extends BaseController {

    @Autowired
    private TbMemberService tbMemberService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getInfo")
    public ResponseResult getInfo(){
        HttpServletRequest request = ServletUtils.getRequest();
        LoginUser user = tokenService.getLoginUser(request);
        if (Objects.isNull(user)){
            return ResponseResult.failure("用户不存在");
        }
        TbMember member = user.getUser();
        return ResponseResult.success(member);
    }

    /**
     * 修改密码
     */

    @PostMapping("/passwordUpdate")
    public ResponseResult updatePassword(@RequestBody PasswordDto passwordDto){

        if (Objects.isNull(passwordDto)){
            return ResponseResult.failure(ResponseConstants.CREATE_ERR);
        }
        LoginUser user = tokenService.getLoginUser(ServletUtils.getRequest());
        if (Objects.isNull(user)){
            return ResponseResult.failure(ResponseConstants.CREATE_ERR);
        }
        boolean flag = user.getPassword().equals(passwordEncoder.encode(passwordDto.getOldPassword()));
        if (!flag){
            return ResponseResult.failure("密码不一致");
        }
        TbMember me = user.getUser();
        me.setUpdateTime(new Date());
        user.setUser(me);
        //刷新token
        tokenService.refreshToken(user);
        me.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        boolean b = tbMemberService.updateById(me);
        return result(b);
    }

    @GetMapping("/list")
    public ResponseResult getUserList(){
        PageDto page = page();
        System.out.println("page-----:"+page);
        LambdaQueryWrapper<TbMember> lqw = new LambdaQueryWrapper<>();
        lqw.eq(!StringUtils.isEmpty(page.getName()),TbMember::getNickName,page.getName())
                .and(!StringUtils.isEmpty(page.getType()),va ->va.eq(TbMember::getIsAdmin,page.getType()))
                .and(!StringUtils.isEmpty(page.getState()),va ->va.eq(TbMember::getStatus,page.getState()));
        Page<TbMember> lists = tbMemberService.page(new Page<>(page.getPage(), page.getSize()), lqw);

        Map<String,Object> re = new HashMap<>();
        re.put("list",lists.getRecords());
        re.put("total",lists.getTotal());
        return ResponseResult.success(re);
    }

    @PutMapping("/delete/{id}")
    public ResponseResult deleteUser(@PathVariable Long id){
        boolean is = tbMemberService.removeById(id);
        return result(is);
    }

    @PostMapping("update")
    public ResponseResult updateUserByAdmin(@RequestBody TbMember tbMember){
        tbMember.setUpdateTime(new Date());
        boolean is = tbMemberService.updateById(tbMember);
        if (is){
            TbMember u = tbMemberService.getById(tbMember.getId());
            LoginUser user = tokenService.getLoginUser(ServletUtils.getRequest());
            if (Objects.isNull(u)){
                user.setUser(u);
                tokenService.refreshToken(user);
            }
        }
        return result(is);
    }

    @PostMapping("/save")
    public ResponseResult addUserByAdmin(@RequestBody TbMember tbMember){
        tbMember.setUpdateTime(new Date());
        tbMember.setCreateTime(new Date());
        tbMember.setIsDelete(1);
        tbMember.setStatus("1");

        boolean is = tbMemberService.save(tbMember);
        return result(is);
    }

    @PutMapping("/state/{id}")
    public ResponseResult state(@PathVariable Long id){
        TbMember byId = tbMemberService.getById(id);
        if (byId.getStatus().equals("1")){
            byId.setStatus("0");
        }else{
            byId.setStatus("1");
        }
        boolean is = tbMemberService.updateById(byId);
        return  result(is);
    }
}
