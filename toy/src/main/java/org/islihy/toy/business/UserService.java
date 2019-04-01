package org.islihy.toy.business;

import org.islihy.toy.annotation.Service;

import java.util.Date;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/15 7:49 PM
 */
@Service
public class UserService {
    public Object getUserById(String id) {
        if("ad".equals(id)){
            User user = new User();

            user.setDate(new Date());
            user.setMethod("POST");
            user.setUserName("李航宇");
            return user;
        }
        return "查无此人";

    }
}
