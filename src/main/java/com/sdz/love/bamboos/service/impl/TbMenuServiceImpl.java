package com.sdz.love.bamboos.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sdz.love.bamboos.commons.BaseServiceImpl;
import com.sdz.love.bamboos.entity.TbAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sdz.love.bamboos.mapper.TbMenuMapper;
import com.sdz.love.bamboos.entity.TbMenu;
import com.sdz.love.bamboos.service.TbMenuService;
/**
 * @author 13557
 */
@Service
public class TbMenuServiceImpl extends BaseServiceImpl<TbMenuMapper, TbMenu> implements TbMenuService{

    @Autowired
    private TbMenuMapper tbMenuMapper;

    @Override
    public Set<String> selectMenuPermsByUserId(TbAdmin tbAdmin) {
        List<String> list = tbMenuMapper.selectMenuPermsByUserId(tbAdmin);
        Set<String> permsSet = new HashSet<>();
        for (String perm : list)
        {
            if (StringUtils.isNotBlank(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
