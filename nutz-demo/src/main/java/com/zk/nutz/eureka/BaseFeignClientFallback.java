package com.zk.nutz.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName: BaseFeignClientFallback
 * @Description:  Feign基类的回退类
 * @author SuXun
 * @date 2018年3月5日 下午5:04:13
 */
public class BaseFeignClientFallback implements BaseFeignClient {

    protected static final Logger LOG = LoggerFactory.getLogger(BaseFeignClientFallback.class);

    @Override
    public ResultJsonEntity select(Object obj) {
        LOG.error("{} select 出错 进入熔断 ", this.getClass().getName());
        return ResultJsonUtil.returnResult(ResultEnum.FAIL);
    }

    @Override
    public ResultJsonEntity selectAll() {
        LOG.error("{} selectAll 出错 进入熔断", this.getClass().getName());
        return ResultJsonUtil.returnResult(ResultEnum.FAIL);
    }

    @Override
    public ResultJsonEntity selectByExample(Map<String, Object> map) {
        LOG.error("{} selectByMap 出错 进入熔断 ", this.getClass().getName());
        return ResultJsonUtil.returnResult(ResultEnum.FAIL);
    }

    @Override
    public ResultJsonEntity selectByPrimaryKey(String key) {
        LOG.error("{} selectByPrimaryKey 出错 进入熔断 ", this.getClass().getName());
        return ResultJsonUtil.returnResult(ResultEnum.FAIL);
    }

    @Override
    public ResultJsonEntity insertSelective(Object obj) {
        LOG.error("{} insertSelective 出错 进入熔断 ", this.getClass().getName());
        return ResultJsonUtil.returnResult(ResultEnum.FAIL);
    }

    @Override
    public ResultJsonEntity updateByPrimaryKeySelective(Object obj) {
        LOG.error("{} updateByPrimaryKeySelective 出错 进入熔断 ", this.getClass().getName());
        return ResultJsonUtil.returnResult(ResultEnum.FAIL);
    }

    @Override
    public ResultJsonEntity getPageExampleList(Map<String, Object> map) {
        LOG.error("{} getPageExampleList 出错 进入熔断 ", this.getClass().getName());
        return ResultJsonUtil.returnResult(ResultEnum.FAIL);
    }
}
