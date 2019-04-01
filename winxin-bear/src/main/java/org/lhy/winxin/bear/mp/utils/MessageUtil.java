package org.lhy.winxin.bear.mp.utils;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lhy.winxin.bear.mp.bean.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息格式的转换
 *
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/3 下午8:24
 */
public class MessageUtil {

    /**
     * xml 转化为Map
     *
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<>(10);
        SAXReader reader = new SAXReader();
        InputStream inputStream = request.getInputStream();
        Document doc = reader.read(inputStream);
        Element root = doc.getRootElement();
        List<Element> lists = root.elements();
        for (Element e : lists) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        return map;
    }

    public static String textMessageToXml(TextMessage textMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    public static String initText(String toUserName, String fromUserName, String content) {
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType(WxMpConstants.Message.MESSAGE_TEXT);
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setContent(content);
        return textMessageToXml(textMessage);
    }

    /**
     * 主菜单
     *
     * @return
     */
    public static String menuText() {
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎你的关注，我是言语二三 ：\n\n");
        sb.append("1、个人博客\n");
        sb.append("2、相关推荐\n");
        sb.append("回复？调出此菜单");
        return sb.toString();
    }
}
