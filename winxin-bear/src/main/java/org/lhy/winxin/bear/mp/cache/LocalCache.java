package org.lhy.winxin.bear.mp.cache;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/12 下午9:59
 */
public class LocalCache {
    /**
     * key值
     */
    private String cacheId;
    /**
     * 缓存对象
     */
    private Object cacheObj;
    /**
     * 最后访问时间
     */
    private long LastAccessTime;

    public LocalCache() {
    }

    public LocalCache(String cacheId, Object cacheObj) {
        this.cacheId = cacheId;
        this.cacheObj = cacheObj;
    }

    public LocalCache(String cacheId, Object cacheObj, long lastAccessTime) {
        this.cacheId = cacheId;
        this.cacheObj = cacheObj;
        LastAccessTime = lastAccessTime;
    }

    public String getCacheId() {
        return cacheId;
    }

    public void setCacheId(String cacheId) {
        this.cacheId = cacheId;
    }

    public Object getCacheObj() {
        return cacheObj;
    }

    public void setCacheObj(Object cacheObj) {
        this.cacheObj = cacheObj;
    }

    public long getLastAccessTime() {
        return LastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        LastAccessTime = lastAccessTime;
    }
}
