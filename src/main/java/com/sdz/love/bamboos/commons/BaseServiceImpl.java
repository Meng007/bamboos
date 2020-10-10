package com.sdz.love.bamboos.commons;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdz.love.bamboos.commons.constant.ResponseConstants;
import com.sdz.love.bamboos.exception.BusinessException;

/**
 * @author 13557
 */

public class BaseServiceImpl<M extends BaseMapper<T>,T extends BaseDomain> extends ServiceImpl<M,T> implements IBaseService<T> {
    /**
     * 通过反射调用 getId()
     */
    private static final String INVOKE_ID = "getId";

    /**
     * 检查字段：ID
     */
    protected static final String ID = "id";

    @Override
    public boolean create(T domain) {
        return super.save(domain);
    }

    @Override
    public boolean remove(Long id) {
        if (checkId(id)){
            return super.removeById(id);
        }
        return false;
    }

    @Override
    public boolean update(T domain) {

        try{
            if (checkId((Long) domain.getClass().getMethod(INVOKE_ID).invoke(domain))){
                return super.updateById(domain);
            }
            return false;
        }catch (Exception e){
            throw new BusinessException(e.getMessage(),520);
        }
    }

    @Override
    public T get(Long id) {
        return super.getById(id);
    }

    @Override
    public IPage<T> page(int current, int size, T domain) {
        Page<T> tPage = new Page<>(current, size);
        LambdaQueryWrapper<T> w = new LambdaQueryWrapper<>();
        return super.page(tPage,w);
    }

    /**
     * 检查 ID 是否存在
     *
     * @param id {@code Long} ID
     * @return {@code boolean} ID 不存在则抛出异常
     */
    protected boolean checkId(Long id) {
        if (!checkUniqueness(ID, id)) {
            throw new BusinessException(ResponseConstants.NONE_DATE_E,530);
        }
        return true;
    }

    protected boolean checkUniqueness(String column, Object value) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq(column, value);
        return super.count(wrapper) > 0;
    }
}
