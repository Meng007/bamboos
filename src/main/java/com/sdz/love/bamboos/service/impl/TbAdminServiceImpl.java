package com.sdz.love.bamboos.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdz.love.bamboos.commons.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.sdz.love.bamboos.mapper.TbAdminMapper;
import com.sdz.love.bamboos.entity.TbAdmin;
import com.sdz.love.bamboos.service.TbAdminService;
import org.springframework.util.StringUtils;

/**
 * @author 13557
 */
@Service
public class TbAdminServiceImpl extends BaseServiceImpl<TbAdminMapper,TbAdmin> implements TbAdminService {


    @Override
    public boolean create(TbAdmin domain) {
        return super.create(domain);
    }

    @Override
    public IPage<TbAdmin> page(int current, int size, TbAdmin domain) {

        Page<TbAdmin> page = new Page(current, size);
        LambdaQueryWrapper<TbAdmin> w = new LambdaQueryWrapper<>();
        // id
        w.eq(!StringUtils.isEmpty(domain.getId()),TbAdmin::getId,domain.getId())
                //昵称
                .or().like(!StringUtils.isEmpty(domain.getNickName()),TbAdmin::getNickName,domain.getNickName())
                .or().like(!StringUtils.isEmpty(domain.getPhoneNumber()),TbAdmin::getPhoneNumber,domain.getPhoneNumber());
        return super.page(page, w);
    }
}


