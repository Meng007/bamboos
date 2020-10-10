package com.sdz.love.bamboos.controller;

import com.alibaba.druid.sql.dialect.h2.visitor.H2ASTVisitor;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdz.love.bamboos.commons.BaseController;
import com.sdz.love.bamboos.commons.ResponseResult;
import com.sdz.love.bamboos.commons.config.SDZConfig;
import com.sdz.love.bamboos.commons.dto.PageDto;
import com.sdz.love.bamboos.entity.*;
import com.sdz.love.bamboos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {


    @Autowired
    TbGoodsTypeService tbGoodsTypeService;
    @Autowired
    TbLbService tbLbService;
    @Autowired
    TbGoodsService tbGoodsService;
    @Autowired
    TbLoveService tbLoveService;
    @Autowired
    TbNoticeService tbNoticeService;

    @Autowired
    SDZConfig sdzConfig;

    @Autowired
    TbAboutService tbAboutService;

    @GetMapping("/lb")
    public ResponseResult lb(){
        LambdaQueryWrapper<TbLb> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TbLb::getState,1);
        List<TbLb> list = tbLbService.list(lqw);
        return ResponseResult.success(list);
    }

    @GetMapping("/lost")
    public ResponseResult lost(){
       return list("l");
    }

    @GetMapping("/take")
    public ResponseResult take(){
     return list("t");
    }

    private ResponseResult list(String type){
        LambdaQueryWrapper<TbGoods> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TbGoods::getStatus,1).and(true,value ->value.eq(TbGoods::getType,type)).orderBy(true,false,TbGoods::getCreateTime);
        Page<TbGoods> page = tbGoodsService.page(new Page<>(1, 10), lqw);
        return ResponseResult.success(page.getRecords());
    }

    @GetMapping("about")
    public ResponseResult about(){
        List<TbAbout> list = tbAboutService.list();
        return ResponseResult.success(list.get(0));
    }


    @GetMapping("/page/lost")
    public ResponseResult lostPage(){
        return page("l");
    }
    @GetMapping("/page/take")
    public ResponseResult takePage(){
        return page("t");
    }
    private ResponseResult page(String s){
        PageDto page = page();
        LambdaQueryWrapper<TbGoods> l = new LambdaQueryWrapper<>();
        l.eq(TbGoods::getStatus,1).and(true,var ->var.eq(TbGoods::getType,s))
                .and(!StringUtils.isEmpty(page.getName()),var ->var.eq(TbGoods::getGoodsType,page.getName()));

        Page<TbGoods> list = tbGoodsService.page(new Page<>(page.getPage(), page.getSize()), l);
        Map<String,Object> re = new HashMap<>();
        re.put("list",list.getRecords());
        re.put("total",list.getTotal());
        return  ResponseResult.success(re);
    }

    @GetMapping("type")
    public ResponseResult typeList(){
        List<TbGoodsType> list = tbGoodsTypeService.list();
        return ResponseResult.success(list);    }

        @GetMapping("notice")
    public ResponseResult notice(){
        LambdaQueryWrapper<TbNotice> l = new LambdaQueryWrapper<>();
        l.eq(TbNotice::getState,1);
            List<TbNotice> list = tbNoticeService.list(l);
            return ResponseResult.success(list);
    }

    @GetMapping("config")
    public ResponseResult config(){
        return ResponseResult.success(sdzConfig);
    }
}
