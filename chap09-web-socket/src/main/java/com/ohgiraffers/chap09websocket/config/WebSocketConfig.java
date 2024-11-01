package com.ohgiraffers.chap09websocket.config;

import com.ohgiraffers.chap09websocket.server.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            // WebSocket 핸들러를 등록하는 메소드
        registry.addHandler(new ChatWebSocketHandler(),
                "/chattingServer").setAllowedOrigins("*");
    }

}
