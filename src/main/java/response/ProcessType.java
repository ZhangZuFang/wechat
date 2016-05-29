package main.java.response;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import main.java.message.response.Article;
import main.java.message.response.ImgContentRespMsg;
import main.java.message.response.TextResponseMessage;
import main.java.util.XmlMapTransform;

public class ProcessType {

    XmlMapTransform xmlMapTransform = new XmlMapTransform();

    TextResponseMessage textResponceMessage = null;

    ImgContentRespMsg imgContentRespMsg = null;

    String result = "";

    // 图文信息
    public String processNews(Map<String, String> map) {
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");

        List articlesList = new ArrayList<Article>();
        
        Article article = new Article();
        String title = "华为十年的冷板凳";
        String description = "在最近的媒体上，关于华为的消息虽不是占据头条，出场的次数却显然增加。。。。。。";
        String picUrl = "http://a.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=d0392f3d259759ee5e5d6899d3922873/5ab5c9ea15ce36d30dfac80039f33a87e950b100.jpg";
        String url = "http://finance.ifeng.com/a/20150202/13476081_0.shtml";
        article.setTitle(title);
        article.setDescription(description);
        article.setPicUrl(picUrl);
        article.setUrl(url);
       
        articlesList.add(article);
        
        Article article1 = new Article();
        String title1 = "知乎上的48条神回复，有几条还真见血";
        String description1 = "为什么部分人会产生“聪明智慧的姑娘都被憨憨的小伙儿搞定了”的印象？"+
      "   答：严肃地说，我觉得，要么姑娘只是看起来聪明，要么小伙儿只是看起来憨……";
        String picUrl1 = "http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=http://mmbiz.qpic.cn/mmbiz/NRlyD84xy2tkj8u7IFv4UE1ia6JS09Oj0ycTOguTgtrrIRruTNEFbMVRCia8ml2oaAomribCtuibHXt0qIiaHvI8zZQ/0?wx_fmt=jpeg";
        String url1 = "http://chuansong.me/n/331914941871";
        article1.setTitle(title1);
        article1.setDescription(description1);
        article1.setPicUrl(picUrl1);
        article1.setUrl(url1);
        articlesList.add(article1);
        

        imgContentRespMsg = new ImgContentRespMsg();
        imgContentRespMsg.setFromUserName(ToUserName);
        imgContentRespMsg.setToUserName(FromUserName);
        imgContentRespMsg.setMsgType(msgType);
        imgContentRespMsg.setArticleCount(2);
        imgContentRespMsg.setArticles(articlesList);

        result = xmlMapTransform.imgContentMsg2Xml(imgContentRespMsg);
        return result;
    }

    // 文本消息处理
    public String processTxt(Map<String, String> map) throws UnsupportedEncodingException {

        // 开发者微信号
        String toUserName = map.get("ToUserName");
        // 发送方帐号（一个OpenID）
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        // 文本消息内容
        String content = map.get("Content");
        System.out.println("-------消息内容 ：-------" + content + "-------------------");
        if (content.equals("新闻")) {
            map.put("MsgType", "news");
            System.out.println("---------------请求 新闻 ----------------------------");
            result = processNews(map);

        } else if (content.equals("frfr")) {

            textResponceMessage = new TextResponseMessage();
            String responseContent = "你说 : " + content + " , 但是我就假装没听到 ！哦啦啦！哦嘞嘞！"
                    + "<a href=\" http://chuansong.me/n/331914941871\">" + "不过可以看下这里！嘻嘻！" + "</a>";

            textResponceMessage.setContent(responseContent);
            textResponceMessage.setFromUserName(toUserName);
            textResponceMessage.setToUserName(fromUserName);

            long createTime = new Date().getTime();
            textResponceMessage.setCreateTime(createTime);

            textResponceMessage.setMsgType(msgType);

            result = xmlMapTransform.txtResMesg2Xml(textResponceMessage);
        }
        return result;
    }

    // 图片消息
    public String processImg(Map<String, String> map) {
        System.out.println("-----------------deal with image infor-------------------");
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String media_Id = map.get("MediaId");

        return result = "<xml><ToUserName><![CDATA[" + FromUserName + "]]></ToUserName>" + " <FromUserName><![CDATA["
                + ToUserName + "]]></FromUserName>" + "<CreateTime>12345678</CreateTime>" + "<MsgType><![CDATA["
                + msgType + "]]></MsgType>" + "<Image><MediaId><![CDATA[" + media_Id + "]]></MediaId></Image></xml>";

    }

    // 處理“語音”消息
    public String processVoice(Map<String, String> map) {
        System.out.println("處理語音消息");
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String media_id = map.get("MediaId");

        return result = "<xml><ToUserName><![CDATA[" + FromUserName + "]]></ToUserName>" + " <FromUserName><![CDATA["
                + ToUserName + "]]></FromUserName>" + "<CreateTime>12345678</CreateTime>" + "<MsgType><![CDATA["
                + msgType + "]]></MsgType>" + "<Voice><MediaId><![CDATA[" + media_id + "]]></MediaId></Voice></xml>";
    }

    public String processVideo(Map<String, String> map) {
        System.out.println("處理視屏消息");
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String media_id = map.get("MediaId");

        return result = "<xml><ToUserName><![CDATA[" + FromUserName + "]]></ToUserName>" + " <FromUserName><![CDATA["
                + ToUserName + "]]></FromUserName>" + "<CreateTime>12345678</CreateTime>" + "<MsgType><![CDATA["
                + msgType + "]]></MsgType>" + "<Video><MediaId><![CDATA[" + media_id + "]]></MediaId>"
                + "</Video></xml>";
    }

    public String processLoction(Map<String, String> parameterMap) {
        // TODO Auto-generated method stub
        return null;
    }

    public String processEvent(Map<String, String> map) {
        System.err.println("扫码");
        String eventType = map.get("Event");

        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String msgType = map.get("msgType");

        // 订阅
        if (eventType.equals("subscribe")) {
            System.out.println("进行关注的处理");
            result = "<xml><ToUserName><![CDATA[" + FromUserName + "]]></ToUserName>" + " <FromUserName><![CDATA["
                    + ToUserName + "]]></FromUserName>" + "<CreateTime>12345678</CreateTime>" + "<MsgType><![CDATA["
                    + msgType + "]]></MsgType>" + "<Event><![CDATA[" + eventType + "]]></Event></xml>";
        }
        // 取消订阅
        else if (eventType.equals("unsubscribe")) {
            // TODO
            return null;
        }
        // 点击菜单
        else if (eventType.equals("CLICK")) {
            // TODO
            return null;
        }
        return result;
    }
}
