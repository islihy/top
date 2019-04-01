package org.islihy.toy.business;

import java.util.Date;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 8:25 PM
 */
public class User {
    private String userName;

    private String method;

    private Date date;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
