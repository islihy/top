package org.lhy.winxin.bear.mp.api.impl;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.lhy.winxin.bear.mp.api.WxMpService;
import org.lhy.winxin.bear.mp.bean.AccessToken;
import org.lhy.winxin.bear.mp.utils.CheckUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 下午12:41
 */
@Service
public class WxMpServiceImpl implements WxMpService {


    @Override
    public boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        return CheckUtil.checkSignature(signature,timestamp,nonce, token);
    }

    @Override
    public String getAccessToken(String grant_type,String appid,String secret)throws IOException{
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response1 = httpclient.execute(httpGet);

        HttpEntity entity1 = response1.getEntity();
        AccessToken accessToken = new Gson().fromJson(EntityUtils.toString(entity1),AccessToken.class);

        response1.close();
        return accessToken.getAccess_token();
    }
}
