package org.lhy.winxin.bear.mp.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/12 下午9:58
 */
public class LocalCacheManager {
    private ConcurrentHashMap<String,LocalCache> cacheContainer = null;
    private static volatile LocalCacheManager m_cacheManager = null;
    private long timeout;


    public static LocalCacheManager getInstance() {
        if(m_cacheManager == null){
            synchronized (LocalCacheManager.class){
                if(m_cacheManager == null){
                    m_cacheManager = new LocalCacheManager(1000*5);
                }
            }
        }
        return m_cacheManager;
    }

    private LocalCacheManager(long timeout) {
        this.timeout = timeout;
        cacheContainer = new ConcurrentHashMap<>();
        CacheCleaner cacheCleanerThread = new CacheCleaner();
        cacheCleanerThread.start();
    }

    /**
     * 访问缓存
     * 每一次访问把最后访问时间改成当前时间
     *
     * @param key
     * @return
     */
    public LocalCache getCache(String key){
        LocalCache myCache = cacheContainer.get(key);
        if (myCache!=null){
            myCache.setLastAccessTime(System.currentTimeMillis());
            cacheContainer.put(key,myCache);
        }
        return myCache;
    }

    /**
     * 添加缓存
     *
     * 默认最后访问时间为当期时间
     * @param key
     * @param value
     */
    public void setCache(String key,LocalCache value){
        cacheContainer.remove(key);
        LocalCache myCache = new LocalCache(key,value.getCacheObj());
        myCache.setLastAccessTime(System.currentTimeMillis());
        cacheContainer.put(key,myCache);
    }

    /**
     * 缓存定时清理线程
     *
     */
    class CacheCleaner extends Thread{

        @Override
        public void run() {
            while(true){
                /**
                 * 30分钟执行一次清理
                 */
                try {
                    Thread.sleep(1000*5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (Map.Entry<String,LocalCache> entry:cacheContainer.entrySet() ) {
                    String sessionID = entry.getKey();
                    LocalCache myCache = entry.getValue();
                    if(System.currentTimeMillis() - myCache.getLastAccessTime() >= timeout){
                        cacheContainer.remove(sessionID);
                    }
                }
            }
        }
    }
}
