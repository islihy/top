package org.lhy.winxin.bear.mp.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/13 上午11:29
 */
public class FileUtils {

    /**
     *
     * 创建临时文件
     *
     * @param inputStream
     * @param name  文件名
     * @param ext  扩展名
     * @param tmpDirFile  临时文件夹目录
     * @return
     */
    public static File createTmpFile(InputStream inputStream,String name,String ext,File tmpDirFile)throws IOException{
        File tmpFile;
        if (tmpDirFile==null){
            tmpFile = File.createTempFile(name,"."+ext);
        }else{
            tmpFile = File.createTempFile(name,"."+ext,tmpDirFile);
        }

        tmpFile.deleteOnExit();

        return null;
    }

    /**
     *
     * @param inputStream
     * @param name  文件名
     * @param ext  扩展名
     * @return
     * @throws IOException
     */
    public static File createTmpFile(InputStream inputStream,String name,String ext)throws IOException{
        return createTmpFile(inputStream, name, ext,null);
    }
}
