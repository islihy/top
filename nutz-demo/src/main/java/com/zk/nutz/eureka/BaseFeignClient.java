package com.zk.nutz.eureka;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Map;

/**
 * @ClassName: BaseFeignClient
 * @Description: Feign基类
 * @author SuXun
 * @date 2018年3月5日 下午1:25:59
 */
// @Herders里边的键值对冒号后面必须有个空格！
@Headers({ "Content-Type: application/json", "Accept: application/json" })
public interface BaseFeignClient {

    @RequestLine("POST /select")
    ResultJsonEntity select(Object obj);

    @RequestLine("GET /selectAll")
    ResultJsonEntity selectAll();

    // 因为Example无法被序列化成json，所以参数为Map
    @RequestLine("POST /selectByExample")
    ResultJsonEntity selectByExample(Map<String, Object> map);

    @RequestLine("GET /selectByPrimaryKey/{key}")
    ResultJsonEntity selectByPrimaryKey(@Param("key") String key);

    @RequestLine("POST /insertSelective")
    ResultJsonEntity insertSelective(Object obj);

    @RequestLine("POST /updateByPrimaryKeySelective")
    ResultJsonEntity updateByPrimaryKeySelective(Object obj);

    /**
     * @param map key包含：int pageNum,int rowNum,T record和查询条件
     * @return
     */
    @RequestLine("POST /getPageExampleList")
    ResultJsonEntity getPageExampleList(Map<String, Object> map);

}
