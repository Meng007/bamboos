package com.sdz.love.bamboos.controller;

import com.sdz.love.bamboos.commons.BaseController;
import com.sdz.love.bamboos.commons.ResponseResult;
import com.sdz.love.bamboos.commons.config.service.LoginUser;
import com.sdz.love.bamboos.commons.config.service.TokenService;
import com.sdz.love.bamboos.commons.util.ServletUtils;
import com.sdz.love.bamboos.entity.TbAbout;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.service.TbAboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("about")
public class AboutController extends BaseController {

    @Autowired
    private TbAboutService tbAboutService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("save")
    public ResponseResult save(@RequestBody TbAbout tbAbout){

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        TbMember user = loginUser.getUser();

        tbAbout.setCreateBy(user.getNickName());
        tbAbout.setCreateTime(new Date());
        tbAbout.setState(1);
        tbAbout.setIcon(user.getIcon());
        boolean is = false;
        if(Objects.isNull(tbAbout.getId())){
             is = tbAboutService.save(tbAbout);
        }else {
            is = tbAboutService.updateById(tbAbout);
        }

        return result(is);
    }

    @PostMapping("update")
    public ResponseResult update(@RequestBody TbAbout tbAbout){
        boolean is = tbAboutService.updateById(tbAbout);
        return result(is);
    }

    @GetMapping("list")
    public ResponseResult list(){
        List<TbAbout> list = tbAboutService.list();
        return ResponseResult.success(list.get(0));
    }
}
