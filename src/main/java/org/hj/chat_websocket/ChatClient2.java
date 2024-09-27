/*
package org.hj.chat_websocket;

import javax.swing.*;
import javax.websocket.*;
import java.awt.*;
import java.net.URI;

@ClientEndpoint
public class ChatClient2 extends JFrame {

    private JTextField inputField;
    private JTextArea chatArea;
    private Session session;

    public ChatClient2(String uri) {
        SwingUtilities.invokeLater(() -> {
            // GUI 구성 코드
            setTitle("채팅 클라이언트");
            setSize(400, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            chatArea = new JTextArea();
            chatArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(chatArea);

            inputField = new JTextField();
            inputField.addActionListener(e -> sendMessage());

            add(scrollPane, BorderLayout.CENTER);
            add(inputField, BorderLayout.SOUTH);

            setVisible(true);
        });

        // 웹소켓 연결
        connectWebSocket(uri);
    }

    private void connectWebSocket(String uri) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "서버에 연결할 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(message);
                inputField.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "서버와의 연결이 끊어졌습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 웹소켓 이벤트 핸들러
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        appendMessage("서버에 연결되었습니다.");
    }

    @OnMessage
    public void onMessage(String message) {
        appendMessage(message);
    }

    @OnClose
    public void onClose() {
        appendMessage("서버와의 연결이 종료되었습니다.");
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    private void appendMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            chatArea.append(message + "\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());
        });
    }

    public static void main(String[] args) {
        // 서버 URI 설정 (예: ws://localhost:8080/chat/roomId)
        String serverUri = "ws://localhost:8080/chat/?1234";
        new ChatClient2(serverUri);
    }
}
*/
