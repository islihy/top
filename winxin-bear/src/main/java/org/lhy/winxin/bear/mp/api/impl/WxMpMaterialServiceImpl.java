package org.lhy.winxin.bear.mp.api.impl;

import org.lhy.winxin.bear.mp.api.WxMpMaterialService;

import java.io.File;
import java.io.InputStream;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/13 上午11:58
 */
public class WxMpMaterialServiceImpl implements WxMpMaterialService{

    @Override
    public void uploadMedia(String mediaType, File file,String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+accessToken+"&type="+mediaType;

    }

    @Override
    public void uploadMedia(String mediaType, String fileType, InputStream inputStream,String accessToken) {

    }
}
