package com.sdz.love.bamboos.commons;

import java.util.HashMap;
import java.util.Map;

public class DataWrap {
    private Map<String,Object> data;

    private DataWrap(Map map){
        this.data = map;
    }
    public static DataWrap Instance(){
        return new DataWrap(new HashMap(4));
    }

    public DataWrap put(String key,Object value){
        data.put(key,value);
        return this;
    }
}
