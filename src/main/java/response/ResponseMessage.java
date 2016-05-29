package main.java.response;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.util.XmlMapTransform;

import org.dom4j.DocumentException;

public class ResponseMessage {

    String result = "";

    ProcessType processType = new ProcessType();

    public String getResponse(HttpServletRequest request, HttpServletResponse response) throws IOException,
            DocumentException {
        @SuppressWarnings("unchecked")
        Map<String, String> parameterMap = XmlMapTransform.Xml2Map(request);

        // 先取出“消息类型”进行判断
        String msgType = parameterMap.get("MsgType");
        
        // 文本消息
        if (msgType.equals("text")) {
            System.out.println("-----------------mesgType is  text--------------------");
            
            result = processType.processTxt(parameterMap);
            System.out.println("------result  ：------"+result+"---------------");
            return result;
        }
        // 图文信息
        else if (msgType.equals("news")) {
            
            return result = processType.processNews(parameterMap);
            
        }
        // 图片消息
        else if (msgType.equals("image")) {
            System.out.println("-----------------mesgType is  image--------------------");
            
            return result = processType.processImg(parameterMap);
        }
        // 声音消息
        else if (msgType.equals("voice")) {
            return result = processType.processVoice(parameterMap);
        }
        // 视频消息
        else if (msgType.equals("video")) {
            return result = processType.processVideo(parameterMap);
        }
        // 地理位置
        else if (msgType.equals("location")) {
            System.out.println("消息類型"+msgType);
            System.out.println("處理地理位置");
            return result = processType.processLoction(parameterMap);
        }
        // 事件类型
        else if (msgType.equals("event")) {
            return result = processType.processEvent(parameterMap);
        }
        return result;
    }
}
