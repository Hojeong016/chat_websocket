/*
package org.hj.chat_websocket;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
class WebSocketConfigTest {

    @Test
    public void testWebSocketConnection() throws Exception {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        CompletableFuture<String> futureMessage = new CompletableFuture<>();

        webSocketClient.doHandshake(new TextWebSocketHandler() {
            @Override
            protected void handleTextMessage(org.springframework.web.socket.WebSocketSession session, TextMessage message) {
                futureMessage.complete(message.getPayload());
            }
        }, "ws://localhost:8080/chat");

        // 메시지 전송 및 수신 확인
        String receivedMessage = futureMessage.get(5, TimeUnit.SECONDS);
        assertEquals("환영합니다", receivedMessage); // 서버가 보내는 환영 메시지 확인
    }
}
*/
