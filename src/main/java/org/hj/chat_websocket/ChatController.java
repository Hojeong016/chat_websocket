package org.hj.chat_websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

//// 웹 페이지 반환 컨트롤러
@Controller
public class ChatController {

    @GetMapping("/")
    public  String index(){
        return "index";
    }
    @GetMapping("/chat/room/{roomId}")
    public String chat(@PathVariable String roomId) {
        return "chat";
    }

}
