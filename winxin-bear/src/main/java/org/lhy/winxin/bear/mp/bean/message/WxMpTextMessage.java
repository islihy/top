package org.lhy.winxin.bear.mp.bean.message;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 上午3:15
 */
public class WxMpTextMessage extends WxMpBaseMessage {
    private static final long serialVersionUID = 1L;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
