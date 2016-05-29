package main.java.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.response.ResponseMessage;
import main.java.util.SignUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ForWechat {
    ResponseMessage responseMessage = new ResponseMessage();

    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public void linkByGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        System.out.println("----------------------验证请求是否来自“微信服务器”，被doGet()接收---------------------------");
        // 微信加密签名
        String signature = request.getParameter("signature");
        System.out.println("消息体加密签名 ：" + signature);
        // 时间戮
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 通过检验 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    @RequestMapping(value = "/index.do", method = RequestMethod.POST)
    public void ProcessPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("=------------------请求Post方式-------------------");

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戮
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 仍然需要对传递过来的信息进行验证，但是返回的不是随机数，是回复信息

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            String respMessage = null;
            try {
                respMessage = responseMessage.getResponse(request, response);
            } catch (Exception e) {
            }
            PrintWriter out = response.getWriter();
            out.print(respMessage);
            out.close();
            out = null;
        }
    }

}
