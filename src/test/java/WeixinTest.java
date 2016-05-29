package test.java;

import org.junit.Test;

public class WeixinTest {
    
    @Test
    public void  textInforTest(){
        
        String textInfor=    "<xml><ToUserName><![CDATA["+"fds"+"]]></ToUserName>"+
                " <FromUserName><![CDATA["+"hyy"+"]]></FromUserName>"+
                "<CreateTime>12345678</CreateTime>"+
               "<MsgType><![CDATA["+"text"+"]]></MsgType>";
        
    }
}
