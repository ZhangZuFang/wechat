package main.java.message.response;

public class TextResponseMessage  extends RespBaseMsg {

    public TextResponseMessage() {
    }

    private String Content;  
    
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
    
}
