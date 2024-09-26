package org.hj.chat_websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//1
@Configuration
@EnableWebSocket // 웹소켓의 기능을 활성화하는 어노테이션
                // -> WebSocketConfigurer 인터체이스를 구현하여서 원하는 경로에 웹소켓 연결을 처리할 수 있다.(경로 지정)
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatHandler chatHandler;

    public WebSocketConfig(ChatHandler chatHandler) {
        this.chatHandler = chatHandler;
    }
//

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "/chat/").setAllowedOrigins("*");
        //* = 모든 도메인에 대한 접근을 허용 -> 보안상 문제가 발생할 수 있다
        //setAllowedOrigins("https://your-domain.com")와 같이 필요한 도메인만 명시화 하기
    }

    @Bean
    public WebSocketHandler socketTextHandler(){
        return chatHandler;
    }
}
