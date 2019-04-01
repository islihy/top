package org.lhy.winxin.bear.mp.api;

import java.io.File;
import java.io.InputStream;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/13 上午11:13
 */
public interface WxMpMaterialService {

    /**
     *
     * @param mediaType 媒体类型, 请看{@link org.lhy.winxin.bear.mp.utils.WxMpConstants}
     * @param file 文件对象
     * @see #uploadMedia(String, String, InputStream,String)
     */
    void uploadMedia(String mediaType, File file,String accessToken);

    /**
     *
     * @param mediaType 媒体类型
     * @param fileType  文件类型
     * @param inputStream 输入流
     *
     */
    void uploadMedia(String mediaType, String fileType, InputStream inputStream,String accessToken);
}
