package org.islihy.toy.business;

import org.islihy.toy.annotation.Controller;
import org.islihy.toy.annotation.Inject;
import org.islihy.toy.annotation.RequestMapping;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/15 7:49 PM
 */
@Controller
public class UserController {

    @Inject
    private UserService userService;

    @RequestMapping("POST:/getUserById")
    public Object getUserById(String id){
        return userService.getUserById(id);
    }
}
