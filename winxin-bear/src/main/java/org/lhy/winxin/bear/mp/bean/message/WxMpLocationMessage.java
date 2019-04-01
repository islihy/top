package org.lhy.winxin.bear.mp.bean.message;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 上午3:15
 */
public class WxMpLocationMessage extends WxMpBaseMessage {
    private static final long serialVersionUID = 1L;

    private String location_X;
    private String location_Y;
    private String scale;
    private String label;

    public String getLocation_X() {
        return location_X;
    }

    public void setLocation_X(String location_X) {
        this.location_X = location_X;
    }

    public String getLocation_Y() {
        return location_Y;
    }

    public void setLocation_Y(String location_Y) {
        this.location_Y = location_Y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
