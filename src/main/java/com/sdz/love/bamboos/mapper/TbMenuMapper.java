package com.sdz.love.bamboos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdz.love.bamboos.entity.TbAdmin;
import com.sdz.love.bamboos.entity.TbMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author 13557
 */
@Mapper
public interface TbMenuMapper extends BaseMapper<TbMenu> {
    List<String> selectMenuPermsByUserId(TbAdmin tbAdmin);
}