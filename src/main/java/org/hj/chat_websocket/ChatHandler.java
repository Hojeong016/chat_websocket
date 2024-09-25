package org.hj.chat_websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

//2
//TextWebSocketHandler = 텍스트 기반 메시지를 처리하는 웹소켓 핸들러
//메세지 처리 로직 구현 클래스
@Slf4j
@Service
public class ChatHandler extends TextWebSocketHandler {

    private final ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    //ConcurrentHashMap : 스레드의 안전한 해시맵을 사용하여 웹소켓 세션을 관리하는 방식
    //여러 스레드에서 동시에 데이터를 읽고 쓰더라도 동시성 문제를 방지합니다. 웹소켓 서버는 다수의 클라이언트와 동시에 상호작용하므로 스레드 안전성이 필수적
    //String : 웹소켓 세션을 식별할 수 있는 키 / 사용자의 id, 세션 id, 토큰등이 고유한 값이 될 수 있다.
    //WebSocketSession : 스프링의 WebSocketSession 객체, 웹소켓 연결된 사용자와의 상호작용을 담당 --// 아직 완벽 이해가 안감
    //

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //사용자가 "Hello Server!"라는 메시지를 보냈다면, 이 메시지를 TextMessage 객체(메세지를 감싸는 컨테이너의 역할)로 표현하고, 그 내용을 getPayload()를 통해 얻을 수 있다.
        String payload = message.getPayload();
        log.info("message:{}", payload);
        // 이 부분에 추후 악의적인 문자의 내용을 검증하는 부분을 추가할 수 있음.

        //String sessionId = session.getId(); // 아이디 검증? 즉 누구에게 세션을 보낼 수 있는가를 세션 아이디와 비교를 통해 설정을 가능하다
        //만약 친구에게만 보낼 수 있다는 로직을 추가하고 싳다면 현재 메세지를 보낸 클라이언트의 세션 아이디를 가져 온 후 해당 유저릐 친구 목록을 가져와 비교를 하면 된다.

        // 브로드캐스트 : 모든 연결된 세션에 브로드 캐스트 = ㅎ현재 서버에 연결된 모든 클라이언트에게 동일한 메세지를 전솔한다,
        try {
            for (WebSocketSession s : sessionMap.values()) {
                if (s.isOpen() /*&& sessionId.equals(s.getId())*/) { // 즉, && 연산자는 첫 번째 조건이 false이면 두 번째 조건을 검증하지 않고 바로 false를 반환 나 왜 이리 멍청??? 이런 기본도 까먹다니...
                    s.sendMessage(new TextMessage(payload)); // 웹 소켓이 연결되어있는 모든 사용자에게 메세지를 전달
                }
            }
        }catch (Exception e) {
            log.info("웹 소켓의 연결이 안되었습니다");
            throw new RuntimeException(e);
        }
    }

    //클라이언트와 서버 간의 웹소켓 연결이 성공적으로 수립되었을 때 호출되는 메서드
    // 초기 환영 메세지를 전달 하거나 세션을 특정 저장소에 저장하는 등의 역할을 수행할 수 있는 공간
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getId(), session);
        log.info("맵 저장 sessionId:{}", session.getId());
        //축하 메세지
        session.sendMessage(new TextMessage("환영합니다" + session.getId() + "님"));
    }


    //사용자가 채팅방을 나가거나 웹소켓 연결이 종료될 때 호출 되는 메서드
    //연결 종료 감지
    //채팅방 애플리케이션에서는 사용자가 나갈 때 그 사용자의 세션을 제거하여 메모리 누수를 방지하고, 다른 사용자들에게 **"사용자가 나갔습니다"**와 같은 알림
    //종료 상태 처리
    //CloseStatus 객체 - 상태 정보를 알 수 있습니다.
    //정상 종료 : (NORMAL 상태), 오류가 발생했는지, 타임아웃인지 등을 (CloseStatus)
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
        log.info("퇴장 sessionId:{}", session.getId());
        session.sendMessage(new TextMessage(session.getId()+"님이 퇴장하였습니다."));
    }
}
