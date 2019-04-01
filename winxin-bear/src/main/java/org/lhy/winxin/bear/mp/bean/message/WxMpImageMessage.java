package org.lhy.winxin.bear.mp.bean.message;

/**
 * 微信图片回复
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 上午3:15
 */
public class WxMpImageMessage extends WxMpBaseMessage {
    private static final long serialVersionUID = 1L;

    private String picUrl;

    private String mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
