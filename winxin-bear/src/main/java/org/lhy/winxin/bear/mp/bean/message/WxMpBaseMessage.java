package org.lhy.winxin.bear.mp.bean.message;


import java.io.Serializable;

/**
 * 微信消息回复基础表
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 上午3:04
 */
public class WxMpBaseMessage implements Serializable{

    private static final long serialVersionUID = 1L;

    private String toUserName;
    private String fromUserName;
    private long createTime;
    private String msgType;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
