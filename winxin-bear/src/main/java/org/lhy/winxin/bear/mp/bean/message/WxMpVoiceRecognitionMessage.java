package org.lhy.winxin.bear.mp.bean.message;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 上午3:15
 */
public class WxMpVoiceRecognitionMessage extends WxMpVoiceMessage {
    private static final long serialVersionUID = 1L;

    private String recognition;

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }
}
