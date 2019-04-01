package org.islihy.toy.business;

import org.islihy.toy.annotation.Controller;
import org.islihy.toy.annotation.Inject;
import org.islihy.toy.annotation.RequestMapping;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/3/14 2:55 PM
 */
@Controller
public class TestController {

    @Inject
    private TestService testService;

    @RequestMapping("GET:/sayHi")
    public Object sayHi(String ss){
        return testService.say("hi");
//        return ss;
    }

}
