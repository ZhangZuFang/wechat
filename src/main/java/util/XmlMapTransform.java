package main.java.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import main.java.message.response.Article;
import main.java.message.response.RespBaseMsg;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XmlMapTransform {

    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    public static Map Xml2Map(HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap<String, String>();

        StringBuffer sb = new StringBuffer();

        // 得到请求中的数据流，流中包含“用户信息”，“信息格式”，“fromUser”，“toUesr”
        InputStream is = request.getInputStream();

        // 把字节流转为“字符流”，编码为“utf-8”
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");

        // 在读出“字符流”的时候，做一下缓存
        BufferedReader br = new BufferedReader(isr);

        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        String xml = sb.toString();

        // 读取输入流
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList) {
            // 对于CDATA区域内的内容，XML解析程序不会处理，而是直接原封不动的输出。
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    public static String txtResMesg2Xml(RespBaseMsg mesgObject) {
        xstream.alias("xml", mesgObject.getClass());
        return xstream.toXML(mesgObject);
    }
    
    public static String imgContentMsg2Xml(RespBaseMsg mesgObject){
        xstream.alias("xml", mesgObject.getClass());  
        xstream.alias("item", new Article().getClass());  
        return xstream.toXML(mesgObject); 
    }
    
    

}
