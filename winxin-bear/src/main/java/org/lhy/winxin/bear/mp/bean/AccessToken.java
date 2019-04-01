package org.lhy.winxin.bear.mp.bean;


/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 下午4:37
 */
public class AccessToken {
    String access_token;
    long expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
