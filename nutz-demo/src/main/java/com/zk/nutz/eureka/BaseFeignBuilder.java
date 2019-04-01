package com.zk.nutz.eureka;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Logger;
import feign.Request.Options;
import feign.Retryer;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: BaseFeignBuilder
 * @Description: 用于构建Feign
 * @author SuXun
 * @date 2018年3月5日 下午3:50:18
 */
public class BaseFeignBuilder {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BaseFeignBuilder.class);
    private static ConcurrentHashMap<String, Object> cacheFeignMap = new ConcurrentHashMap<String, Object>();
    private static ConcurrentHashMap<String, String> cacheAddressMap = new ConcurrentHashMap<String, String>();

    /**
     * 构建HystrixFeign,具有Hystrix提供的熔断和回退功能,JacksonEncoder、JacksonDecoder、Slf4jLogger、Logger.Level.FULL
     * @param apiType 使用feign访问的接口类,如MedBodyClient.class
     * @param
     * @param fallback 回退类
     * @param url 添加网址
     * @return
     */
    public static <T> T buildHystrixFeign(Class<T> apiType, T fallback, String url) {
        // 之前用GsonEncoder()和GsonDecoder()对Date类型支持不好，改成JacksonEncoder和JacksonDecoder，日期转换正常
        return buildHystrixFeign(apiType, fallback, url, new JacksonEncoder(), new JacksonDecoder(),
                new Slf4jLogger(BaseFeignBuilder.class), Logger.Level.FULL);
    }

    /**
     * 构建HystrixFeign,具有Hystrix提供的熔断和回退功能
     * @param apiType 使用feign访问的接口类,如MedBodyClient.class
     * @param fallback 回退类
     * @param url 添加网址
     * @param encoder 编码器
     * @param decoder 解码器
     * @param logger 日志对象
     * @param logLevel 日志级别
     * @return
     */
    public static <T> T buildHystrixFeign(final Class<T> apiType, T fallback, String url, Encoder encoder, Decoder decoder,
                                          Logger logger, Logger.Level logLevel) {
        return HystrixFeign.builder().encoder(encoder).decoder(decoder).logger(logger).logLevel(logLevel)
                //options添加Feign请求响应超时时间
                .options(new Options(60 * 1000, 60 * 1000)).retryer(Retryer.NEVER_RETRY)
                .setterFactory(new SetterFactory() {
                    @Override
                    public Setter create(Target<?> target, Method method) {
                        //添加Hstrix请求响应超时时间
                        return HystrixCommand.Setter
                                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(apiType.getClass().getSimpleName()))
                                .andCommandPropertiesDefaults(
                                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(60 * 1000) // 超时配置
                                );
                    }
                }).target(apiType, url, fallback);
    }

    /**
     * 获取HystrixFeign。缓存有在缓存取，缓存没有重新构建Feign
     * @param apiType 使用feign访问的接口类,如MedBodyClient.class
     * @param clientName clientName 配置文件中的ribbon client名字
     * @param separator 添加网址分割
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getCacheFeign(Class<T> apiType, String clientName, T fallback, String separator) {

        String resultAddress = getResultAddress(clientName);

        String cacheKey = apiType.getName() + "-" + clientName + "-" + fallback.getClass().getName() + "-"
                + resultAddress + separator;
        Object cacheFeign = cacheFeignMap.get(cacheKey);
        if (cacheFeign == null) {
            T buildFeign = buildHystrixFeign(apiType, fallback, resultAddress + separator);
            cacheFeignMap.put(cacheKey, buildFeign);
            return buildFeign;
        } else {
            return (T) cacheFeign;
        }
    }
    /**
     * 获取服务地址，取不到最新地址在缓存取旧地址，有新地址则返回新地址并刷新缓存
     * @param clientName
     * @return
     */
    public static String getResultAddress(String clientName) {
        String recentAddress = null;
        AlanServiceAddress alanServiceAddress = AlanServiceAddressSelector.selectOne(clientName);
        recentAddress = alanServiceAddress == null ? "" : alanServiceAddress.toString();
        String cacheAddress = cacheAddressMap.get(clientName);
        String resultAddress = "";

        if (StringUtils.isBlank(recentAddress)) {
            if (StringUtils.isBlank(cacheAddress)) {
                log.error("服务" + clientName + "无可用地址");
                throw new RuntimeException("服务" + clientName + "无可用地址");
            } else {
                resultAddress = cacheAddress;
            }
        } else {
            resultAddress = recentAddress;
            cacheAddressMap.put(clientName, recentAddress);
        }
        return resultAddress;
    }
}