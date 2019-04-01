package org.islihy.toy.bean;

import org.islihy.toy.util.CastUtil;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 6:26 PM
 */
public class Param {
    private Map<String,Object> paramMap;
    public Param(Map<String,Object> paramMap){
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取long型参数值
     * @param name
     * @return
     */
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String,Object> getMap(){
        return paramMap;
    }

    public Object[] getObjectArr(){
        return paramMap.values().stream().collect(Collectors.toList()).toArray();
    }
}
