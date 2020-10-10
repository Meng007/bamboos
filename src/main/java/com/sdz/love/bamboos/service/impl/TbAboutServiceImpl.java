package com.sdz.love.bamboos.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdz.love.bamboos.mapper.TbAboutMapper;
import com.sdz.love.bamboos.entity.TbAbout;
import com.sdz.love.bamboos.service.TbAboutService;
@Service
public class TbAboutServiceImpl extends ServiceImpl<TbAboutMapper, TbAbout> implements TbAboutService{

}
