package com.sdz.love.bamboos.commons;

import com.sdz.love.bamboos.commons.constant.ResponseConstants;
import com.sdz.love.bamboos.commons.dto.PageDto;
import com.sdz.love.bamboos.commons.util.ServletUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 13557
 */
public abstract class BaseController{

    protected ResponseResult error(){
        return ResponseResult.failure(ResponseConstants.CREATE_ERR);
    }

    protected ResponseResult result(boolean is){
        if (is){
            return ResponseResult.success();
        }
        return ResponseResult.failure(ResponseConstants.CREATE_ERR);
    }

    protected PageDto page(){
        HttpServletRequest request = ServletUtils.getRequest();
        String page = request.getParameter("page");
        String size = request.getParameter("size");

        String state = request.getParameter("state");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        if (StringUtils.isEmpty(page) || StringUtils.isEmpty(size)){
            return new PageDto(1,10,name,type,state);
        }
        return new PageDto(Integer.parseInt(page),Integer.parseInt(size),name,type,state);
    }
}
