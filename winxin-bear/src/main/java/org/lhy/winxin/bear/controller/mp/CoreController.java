package org.lhy.winxin.bear.controller.mp;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.lhy.winxin.bear.config.wx.WxMpProperties;
import org.lhy.winxin.bear.mp.api.impl.WxMpServiceImpl;
import org.lhy.winxin.bear.mp.utils.MessageUtil;
import org.lhy.winxin.bear.mp.utils.WxMpConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/3 下午3:59
 */
@RestController("core")
public class CoreController {

    @Autowired
    private WxMpServiceImpl wxMpService;

    @Autowired
    private WxMpProperties wxMpProperties;

    @GetMapping
    public void wechatGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (!wxMpService.checkSignature(signature,timestamp,nonce, wxMpProperties.getToken())) {
            out.println("非法请求警告");
        }
        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            out.println(echostr);
            out.close();
            out = null;
            return;
        }

        out.close();
        out = null;
    }

    @PostMapping
    public void wechatPost(HttpServletRequest request, HttpServletResponse response)throws IOException{
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            Map<String,String> map = null;
            map = MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String message = null;

            if (WxMpConstants.Message.MESSAGE_TEXT.equals(msgType)){
                if ("1".equals(content)){
                    message = MessageUtil.initText(toUserName,fromUserName,"我是个好人");
                }else if ("2".equals(content)){
                    message = MessageUtil.initText(toUserName,fromUserName,"php是世界上最好的语言");
                }else if ("?".equals(content)||"？".equals(content)){
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.menuText());
                }else{
                    message = MessageUtil.initText(toUserName,fromUserName,"您发送的是: "+content);
                }
            }else if (WxMpConstants.Event.EVENT_EVENT.equals(msgType)){
                String eventType = map.get("Event");
                if (WxMpConstants.Event.EVENT_SUBSCRIBE.equals(eventType)){
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.menuText());
                }
            }
            out.print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }

    }

    @PostConstruct
    public void init() throws ServletException {

    }
}
