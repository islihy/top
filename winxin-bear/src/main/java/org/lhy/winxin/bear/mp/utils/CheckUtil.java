package org.lhy.winxin.bear.mp.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/3 下午6:34
 */
public class CheckUtil {

    public static boolean checkSignature(String signature, String timestamp, String nonce,String token) {

        String[] arr = new String[]{token, timestamp, nonce};
        /**
         * 排序
         */
        Arrays.sort(arr);

        StringBuffer content = new StringBuffer();
        Stream.of(arr).forEach(p -> content.append(p));

        String temp = getSha1(content.toString());
        return signature.equals(temp);
    }

    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * sha1加密
     *
     * @param sourceString
     * @return
     */
    public static String SHA1Encode(String sourceString) {
        String resultString = null;
        try {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    public final static String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString().toUpperCase();
    }
}
