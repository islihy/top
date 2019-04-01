package org.lhy.winxin.bear.mp.api;

import java.io.IOException;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 下午12:34
 */
public interface WxMpService {
    /**
     * <pre>
     * 验证消息的确来自微信服务器
     * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
     * </pre>
     */
    boolean checkSignature(String timestamp, String nonce, String signature,String token);

    /**
     * 获取access_token
     * 为了避免调用超过2000/次的调用次数
     * 1，项目启动时，配置缓存器-redis
     * @param grant_type
     * @param appid
     * @param secret
     * @return
     */
    String getAccessToken(String grant_type,String appid,String secret)throws IOException;
}
