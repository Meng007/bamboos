package com.sdz.love.bamboos.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdz.love.bamboos.entity.TbStudent;
import com.sdz.love.bamboos.mapper.TbStudentMapper;
import com.sdz.love.bamboos.service.TbStudentService;

@Service
public class TbStudentServiceImpl extends ServiceImpl<TbStudentMapper, TbStudent> implements TbStudentService {

}

