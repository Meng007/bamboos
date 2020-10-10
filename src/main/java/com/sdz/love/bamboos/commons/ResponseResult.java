package com.sdz.love.bamboos.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 13557
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {
    private String msg;
    private Integer status;
    private Object data;

    public static ResponseResult success(Object object){
        return new ResponseResult("成功",200,object);
    }
    public static ResponseResult success(){
        return new ResponseResult("成功",200,null);
    }
    public static ResponseResult failure(String error){
        return new ResponseResult(error,500,null);
    }
    public static ResponseResult failure(String error,Integer status){
        return new ResponseResult(error,status,null);
    }
    public static ResponseResult failure(Integer status){
        return new ResponseResult("失败",status,null);
    }
}
