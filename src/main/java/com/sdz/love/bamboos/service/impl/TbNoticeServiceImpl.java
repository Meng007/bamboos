package com.sdz.love.bamboos.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdz.love.bamboos.mapper.TbNoticeMapper;
import com.sdz.love.bamboos.entity.TbNotice;
import com.sdz.love.bamboos.service.TbNoticeService;
@Service
public class TbNoticeServiceImpl extends ServiceImpl<TbNoticeMapper, TbNotice> implements TbNoticeService{

}
