package org.lhy.winxin.bear.mp.bean.message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/4 下午3:00
 */
public class WxMpNewsMessage extends WxMpBaseMessage{

    private static final long serialVersionUID = 1L;

    private int articleCount;
    private List<item> articles = new ArrayList<>(8);

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public List<item> getArticles() {
        return articles;
    }

    public void setArticles(List<item> articles) {
        this.articles = articles;
    }

    public static class item{
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}


