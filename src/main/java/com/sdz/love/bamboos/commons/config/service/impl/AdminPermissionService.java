package com.sdz.love.bamboos.commons.config.service.impl;

import com.sdz.love.bamboos.entity.TbAdmin;
import com.sdz.love.bamboos.service.TbMenuService;
import com.sdz.love.bamboos.service.TbRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/** 管理员权限获取
 * @author 13557
 */
@Component
public class AdminPermissionService {

    @Autowired
    private TbMenuService tbMenuService;

    @Autowired
    private TbRoleService tbRoleService;

    public Set<String> getMenuPermission(TbAdmin a)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (a.getId()==1L)
        {
            perms.add("*:*:*");
        }
        else
        {
            perms.addAll(tbMenuService.selectMenuPermsByUserId(a));
        }
        return perms;
    }
}
