package org.lhy.winxin.bear.mp.bean.message;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 上午3:15
 */
public class WxMpVoiceMessage extends WxMpBaseMessage {
    private static final long serialVersionUID = 1L;

    private String mediaId;

    private String format;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
