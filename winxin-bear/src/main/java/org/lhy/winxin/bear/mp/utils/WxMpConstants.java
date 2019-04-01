package org.lhy.winxin.bear.mp.utils;

/**
 * 微信公众号常量表
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 上午3:18
 */
public final class WxMpConstants {

    private WxMpConstants(){}

    /**
     * 微信-公众号-消息
     */
    public interface Message{
        String MESSAGE_TEXT = "text";
        String MESSAGE_IMAGE = "image";
        String MESSAGE_VOICE = "voice";
        String MESSAGE_VIDEO = "video";
        String MESSAGE_LINK = "link";
        String MESSAGE_LOCATION = "location";
        String MESSAGE_NEWS = "news";
        String MESSAGE_MUSIC = "music";

    }

    /**
     * 微信-公众号-事件
     */
    public interface Event{
        String EVENT_EVENT = "event";
        String EVENT_SUBSCRIBE = "subscribe";
        String EVENT_UNSUBSCRIBE = "unsubscribe";
        String EVENT_CLICK = "CLICK";
        String EVENT_VIEW = "VIEW";
        String EVENT_LOCATION = "LOCATION";
        String EVENT_SCAN = "SCAN";
    }

}
