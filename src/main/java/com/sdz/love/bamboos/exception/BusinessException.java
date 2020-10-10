package com.sdz.love.bamboos.exception;

import lombok.Data;

/** 业务异常
 * @author 13557
 */
@Data
public class BusinessException extends RuntimeException{
    private Integer status;
    public BusinessException(String str,Integer code){
        super(str);
        this.status = code;
    }

    public Integer getStatus() {
        return status;
    }
}
