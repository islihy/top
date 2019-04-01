package org.lhy.winxin.bear.http;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lhy.winxin.bear.mp.bean.AccessToken;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 下午3:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpTest {

    @Test
    public void testGet() throws IOException {
        String appid = "wx00ee142cc1aed80e";
        String appsecret = "11e1b2774f77958dad44158c9df82cd1";


        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        try {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();

            String str = EntityUtils.toString(entity1);
            AccessToken accessToken = new Gson().fromJson(str,AccessToken.class);
            System.out.println(accessToken.toString());
            EntityUtils.consume(entity1);
        } finally {
            response1.close();
        }


    }

    @Test
    public void testPost()throws IOException{

        String appid = "wx00ee142cc1aed80e";
        String appsecret = "11e1b2774f77958dad44158c9df82cd1";


        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("username", "vip"));
        nvps.add(new BasicNameValuePair("password", "secret"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
    }
}
