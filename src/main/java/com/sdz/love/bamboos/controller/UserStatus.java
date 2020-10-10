package com.sdz.love.bamboos.controller;

/**
 * @author 13557
 */

public enum UserStatus {

    OK(0, "正常"), DISABLE(1, "停用"), DELETED(2, "删除");
    private final Integer code;
    private final String msg;
    private UserStatus(Integer code,String str){
        this.code = code;
        this.msg = str;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
