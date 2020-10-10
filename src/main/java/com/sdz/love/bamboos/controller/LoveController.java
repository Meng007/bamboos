package com.sdz.love.bamboos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdz.love.bamboos.commons.BaseController;
import com.sdz.love.bamboos.commons.ResponseResult;
import com.sdz.love.bamboos.commons.config.service.LoginUser;
import com.sdz.love.bamboos.commons.config.service.TokenService;
import com.sdz.love.bamboos.commons.dto.PageDto;
import com.sdz.love.bamboos.commons.util.ServletUtils;
import com.sdz.love.bamboos.entity.TbLove;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.service.TbLoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("love")
public class LoveController extends BaseController {

    @Autowired
    private TbLoveService tbLoveService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("save")
    public ResponseResult save(@RequestBody TbLove tbLove){
        HttpServletRequest request = ServletUtils.getRequest();
        LoginUser loginUser = tokenService.getLoginUser(request);
        TbMember user = loginUser.getUser();
        tbLove.setCreateBy(user.getNickName());
        tbLove.setIcon(user.getIcon());
        tbLove.setCreateTime(new Date());
        tbLove.setState(1);
        tbLove.setCreateId(user.getId());
        tbLove.setIsDelete(1);
        boolean is = tbLoveService.save(tbLove);
        return result(is);
    }

    @PutMapping("/del/{id}")
    public ResponseResult delete(@PathVariable Long id){
        boolean is = tbLoveService.removeById(id);
        return result(is);
    }

    @GetMapping("/u/list")
    public ResponseResult listByUser(){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        TbMember user = loginUser.getUser();

        LambdaQueryWrapper<TbLove> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TbLove::getCreateId,user.getId());
        PageDto page = page();
        Page<TbLove> lists = tbLoveService.page(new Page<>(page.getPage(), page.getSize()));
        long total = lists.getTotal();
        Map<String,Object> re = new HashMap<>();
        re.put("list",lists.getRecords());
        re.put("total",total);
        return ResponseResult.success(re);
    }

    @GetMapping("/index/list")
    public ResponseResult listByIndex(){

        LambdaQueryWrapper<TbLove> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TbLove::getState,1);
        PageDto page = page();
        Page<TbLove> lists = tbLoveService.page(new Page<>(page.getPage(), page.getSize()), lqw);

        Map<String,Object> re = new HashMap<>();
        re.put("list",lists.getRecords());
        re.put("total",lists.getTotal());

        return ResponseResult.success(re);
    }
}
