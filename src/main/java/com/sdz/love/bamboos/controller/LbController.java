package com.sdz.love.bamboos.controller;

import com.sdz.love.bamboos.commons.BaseController;
import com.sdz.love.bamboos.commons.ResponseResult;
import com.sdz.love.bamboos.commons.config.service.LoginUser;
import com.sdz.love.bamboos.commons.config.service.TokenService;
import com.sdz.love.bamboos.commons.util.ServletUtils;
import com.sdz.love.bamboos.entity.TbLb;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.service.TbLbService;
import com.sdz.love.bamboos.service.TbLoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("lb")
public class LbController extends BaseController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private TbLbService tbLbService;
    @PostMapping("save")
    public ResponseResult save(@RequestBody TbLb tbLb){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        TbMember user = loginUser.getUser();
        tbLb.setCreateBy(user.getNickName());
        tbLb.setCreateTime(new Date());
        tbLb.setIsdelete(1);
        tbLb.setState(1);
        boolean is = tbLbService.save(tbLb);
        return  result(is);
    }

    @GetMapping("list")
    public ResponseResult list(){
        List<TbLb> list = tbLbService.list();
        return ResponseResult.success(list);
    }

    @DeleteMapping("del")
    public ResponseResult del(@PathVariable Long id){
        boolean is = tbLbService.removeById(id);
        return ResponseResult.success();
    }

    @PutMapping("state/{id}")
    public ResponseResult upState(@PathVariable Long id){
        TbLb byId = tbLbService.getById(id);
        if (byId.getState() ==1){
            byId.setState(0);
        }else {
            byId.setState(1);
        }
        boolean is = tbLbService.updateById(byId);
        return  result(is);
    }

}
