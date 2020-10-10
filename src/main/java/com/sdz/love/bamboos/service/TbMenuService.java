package com.sdz.love.bamboos.service;

import com.sdz.love.bamboos.commons.IBaseService;
import com.sdz.love.bamboos.entity.TbAdmin;
import com.sdz.love.bamboos.entity.TbMenu;

import java.util.Set;

/**
 * @author 13557
 */
public interface TbMenuService  extends IBaseService<TbMenu> {

    Set<String> selectMenuPermsByUserId(TbAdmin tbAdmin);
}
