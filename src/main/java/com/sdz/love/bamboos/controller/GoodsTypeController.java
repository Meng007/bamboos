package com.sdz.love.bamboos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdz.love.bamboos.commons.BaseController;
import com.sdz.love.bamboos.commons.ResponseResult;
import com.sdz.love.bamboos.commons.config.service.LoginUser;
import com.sdz.love.bamboos.commons.config.service.TokenService;
import com.sdz.love.bamboos.commons.util.ServletUtils;
import com.sdz.love.bamboos.entity.TbGoodsType;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.service.TbGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("type")
public class GoodsTypeController extends BaseController {

    @Autowired
    private TbGoodsTypeService tbGoodsTypeService;

    @Autowired
    private TokenService tokenService;

    /**
     *  保存
     * @param tbGoodsType
     * @return
     */
    @PostMapping("save")
    public ResponseResult save(@RequestBody TbGoodsType tbGoodsType){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        TbMember user = loginUser.getUser();
        tbGoodsType.setCreateBy(user.getNickName());
        tbGoodsType.setCreateTime(new Date());
        tbGoodsType.setIsDelete(1);
        if (StringUtils.isEmpty(tbGoodsType.getPid())){
            tbGoodsType.setPid(0L);
        }
        boolean is = tbGoodsTypeService.save(tbGoodsType);
        return  result(is);
    }

    /**
     * 删除
     */

    @PutMapping("delete/{id}")
    public ResponseResult delete(@PathVariable("id") Long id){
        TbGoodsType type = tbGoodsTypeService.getById(id);
        if (type.getPid()==0L){
            LambdaQueryWrapper<TbGoodsType> lqw = new LambdaQueryWrapper<>();
            lqw.eq(TbGoodsType::getPid,type.getId());
            boolean is = tbGoodsTypeService.remove(lqw);
        }
        boolean is = tbGoodsTypeService.removeById(id);
        return result(is);
    }

    @GetMapping("list")
    public ResponseResult list(){
        List<TbGoodsType> list = tbGoodsTypeService.list();
        return ResponseResult.success(list);
    }


}
