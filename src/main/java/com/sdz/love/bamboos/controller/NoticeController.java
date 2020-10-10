package com.sdz.love.bamboos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdz.love.bamboos.commons.BaseController;
import com.sdz.love.bamboos.commons.ResponseResult;
import com.sdz.love.bamboos.commons.config.service.TokenService;
import com.sdz.love.bamboos.commons.dto.PageDto;
import com.sdz.love.bamboos.commons.util.ServletUtils;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.entity.TbNotice;
import com.sdz.love.bamboos.service.TbNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    @Autowired
    private TbNoticeService tbNoticeService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("save")
    public ResponseResult save(@RequestBody TbNotice tbNotice){

        TbMember user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        tbNotice.setCreateBy(user.getNickName());
        tbNotice.setState(1);
        tbNotice.setIsDelete(1);
        tbNotice.setCreateIcon(user.getIcon());
        tbNotice.setCreateTime(new Date());

        boolean is = tbNoticeService.save(tbNotice);
        return  result(is);
    }

    @PutMapping("/state/{id}")
    public ResponseResult upState(@PathVariable Long id){
        TbNotice byId = tbNoticeService.getById(id);
        if (byId.getState()==1){
            byId.setState(0);
        }else {
            byId.setState(1);
        }
        boolean is = tbNoticeService.updateById(byId);
        return result(is);
    }

    @DeleteMapping("del/{id}")
    public ResponseResult del(@PathVariable Long id){
        boolean is = tbNoticeService.removeById(id);
        return result(is);
    }

    @GetMapping("list")
    public ResponseResult list(){
        PageDto page = page();
        LambdaQueryWrapper<TbNotice> lqw = new LambdaQueryWrapper<>();
        lqw.like(!StringUtils.isEmpty(page.getName()),TbNotice::getTitle,page.getName())
                .and(!StringUtils.isEmpty(page.getType()),var ->var.eq(TbNotice::getCreateBy,page.getType()))
                .and(!StringUtils.isEmpty(page.getState()),var ->var.eq(TbNotice::getState,page.getState()));
        Page<TbNotice> lists = tbNoticeService.page(new Page<>(page.getPage(), page.getSize()), lqw);

        Map<String,Object> re = new HashMap<>();
        re.put("list",lists.getRecords());
        re.put("total",lists.getTotal());
        return ResponseResult.success(re);
    }
}
