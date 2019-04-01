package org.lhy.winxin.bear.mp.cache;

import java.util.Random;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/12 下午10:19
 */
public class TestCache {
    public static void main(String[] args) {

        LocalCacheManager cacheManager = LocalCacheManager.getInstance();
        String sessionID = "test_sessionid";
        Integer random_num = new Random().nextInt();
        System.out.println(random_num);
        LocalCache myCache = new LocalCache(sessionID,random_num);
        cacheManager.setCache(sessionID,myCache);

        //休眠3秒
        try {
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(cacheManager.getCache(sessionID).getCacheObj().toString());
        //休眠3秒
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(cacheManager.getCache(sessionID).getCacheObj().toString());
    }
}
