package com.example.eurekaserver.config;

import lombok.extern.log4j.Log4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.URI;

@Log4j
@Component
@Configuration
public class WebSocketClientConfig {

    @Bean
    public WebSocketClient webSocketClient() {
        try {
            WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://localhost:8180/websocket"),new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    log.info("[websocket] connectSccuess");
                }

                @Override
                public void onMessage(String message) {
                    log.info("[websocket] getMessage:"+message);

                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    log.info("[websocket] exit");
                }

                @Override
                public void onError(Exception ex) {
                    log.info("[websocket] connectError:"+ex.getMessage());
                }
            };
            webSocketClient.connect();
            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

