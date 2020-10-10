package com.sdz.love.bamboos.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdz.love.bamboos.mapper.TbMemberMapper;
import com.sdz.love.bamboos.entity.TbMember;
import com.sdz.love.bamboos.service.TbMemberService;
@Service
public class TbMemberServiceImpl extends ServiceImpl<TbMemberMapper, TbMember> implements TbMemberService{

}
