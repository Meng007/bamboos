package com.sdz.love.bamboos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdz.love.bamboos.commons.BaseController;
import com.sdz.love.bamboos.commons.ResponseResult;
import com.sdz.love.bamboos.commons.config.service.LoginUser;
import com.sdz.love.bamboos.commons.config.service.TokenService;
import com.sdz.love.bamboos.commons.dto.PageDto;
import com.sdz.love.bamboos.commons.util.ServletUtils;
import com.sdz.love.bamboos.entity.TbGoods;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.service.TbGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.sdz.love.bamboos.commons.constant.ResponseConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13557
 */
@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController {

    @Autowired
    private TbGoodsService tbGoodsService;

    @Autowired
    private TokenService tokenService;

    /**
     *  添加
     * @param tbGoods
     * @return
     */
    @PostMapping("save")
    public ResponseResult release(@RequestBody TbGoods tbGoods){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        TbMember user = loginUser.getUser();
        tbGoods.setCreateBy(user.getNickName());
        tbGoods.setCreateIcon(user.getIcon());
        tbGoods.setCreateTime(new Date());
        tbGoods.setUserId(user.getId());
        tbGoods.setStatus(1);
        tbGoods.setDeleteFlag(1);
        boolean save = tbGoodsService.save(tbGoods);
        return result(save);
    }

    /**
     * 修改
     * @param tbGoods
     * @return
     */
    @PostMapping("update")
    public ResponseResult update(TbGoods tbGoods){
        boolean b = tbGoodsService.updateById(tbGoods);
        return result(b);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PutMapping("delete/{id}")
    public ResponseResult delete(@PathVariable Long id){
        boolean b = tbGoodsService.removeById(id);
        if (b){
            return ResponseResult.success();
        }
        return error();
    }



    /**
     * 查询
     * @return
     */
    @GetMapping("list")
    public ResponseResult list(){
        PageDto page = page();
        LambdaQueryWrapper<TbGoods> lqw = new LambdaQueryWrapper<>();
        lqw.like(!StringUtils.isEmpty(page.getName()),TbGoods::getGoodsName,page.getName())
                .and(!StringUtils.isEmpty(page.getType()),var ->var.like(TbGoods::getGoodsType,page.getType()))
                .and(!StringUtils.isEmpty(page.getState()),var ->var.eq(TbGoods::getStatus,page.getState()));
        Page<TbGoods> lists = tbGoodsService.page(new Page<>(page.getPage(), page.getSize()), lqw);

        Map<String,Object> re = new HashMap<>();
        re.put("list",lists.getRecords());
        re.put("total",lists.getTotal());
        return ResponseResult.success(re);
    }

    @PutMapping("/state/{id}")
    public ResponseResult upstate(@PathVariable Long id){
        TbGoods byId = tbGoodsService.getById(id);
        if (byId.getStatus() ==1){
            byId.setStatus(0);
        }else {
            byId.setStatus(1);
        }
        boolean is = tbGoodsService.updateById(byId);
        return  result(is);
    }

    @GetMapping("/user/list")
    public ResponseResult userGoods(){
        TbMember user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        PageDto page = page();
        LambdaQueryWrapper<TbGoods> l = new LambdaQueryWrapper<>();
        l.eq(TbGoods::getUserId,user.getId())
                .and(!StringUtils.isEmpty(page.getName()),val->val.like(TbGoods::getGoodsName,page.getName()))
                .and(!StringUtils.isEmpty(page.getType()),var ->var.like(TbGoods::getGoodsType,page.getType()))
                .and(!StringUtils.isEmpty(page.getState()),var ->var.eq(TbGoods::getStatus,page.getState()));

        Page lists = tbGoodsService.page(new Page(page.getPage(), page.getSize()), l);
        Map<String,Object> re = new HashMap<>();
        re.put("list",lists.getRecords());
        re.put("total",lists.getTotal());
        return ResponseResult.success(re);
    }
}
