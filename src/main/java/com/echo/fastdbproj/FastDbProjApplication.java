package com.echo.fastdbproj;

import com.echo.fastdbproj.util.JsonProvider;
import org.jetbrains.annotations.NotNull;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@MapperScan("com.echo.fastdbproj.dao")
@EnableWebSocket
public class FastDbProjApplication implements WebSocketConfigurer {
    JsonProvider provider;

    public static void main(String[] args) {
        SpringApplication.run(FastDbProjApplication.class, args);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler(), "/api/websocket").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler wsHandler() {
        return new WebSocketHandler() {
            JsonProvider jsonProvider;
            @Override
            public void afterConnectionEstablished(@NotNull WebSocketSession session) throws Exception {
                System.out.println("链接建立成功");
            }

            @Override
            public void handleMessage(@NotNull WebSocketSession session, @NotNull WebSocketMessage<?> message) throws Exception {
                System.out.println(jsonProvider.toJson(message));
            }

            @Override
            public void handleTransportError(@NotNull WebSocketSession session, @NotNull Throwable exception) throws Exception {

            }

            @Override
            public void afterConnectionClosed(@NotNull WebSocketSession session, @NotNull CloseStatus closeStatus) throws Exception {

            }

            @Override
            public boolean supportsPartialMessages() {
                return false;
            }

            @Autowired
            public void setProvider(JsonProvider provider) {
                this.jsonProvider = provider;
            }

        };
    }

    @Bean("fileBasePath")
    public String fileContainer() {
        return "D:\\#__Coding_Projects\\Spring\\UPLOADED_DATA\\";
    }

    @Bean("mapC")
    public Map<String, List<Double>> customerPlaceMap() {
        return new ConcurrentHashMap<>();
    }

    @Bean("mapD")
    public Map<String, List<Double>> driverPlaceMap() {
        return new ConcurrentHashMap<>();
    }

    @Autowired
    public void setProvider(JsonProvider provider) {
        this.provider = provider;
    }
}
