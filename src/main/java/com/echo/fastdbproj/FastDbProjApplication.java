package com.echo.fastdbproj;

import com.echo.fastdbproj.dao.CustomerDao;
import com.echo.fastdbproj.service.MainService;
import com.echo.fastdbproj.util.BinUtils;
import com.echo.fastdbproj.util.JsonProvider;
import org.jetbrains.annotations.NotNull;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
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
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

@SpringBootApplication
@MapperScan("com.echo.fastdbproj.dao")
@EnableWebSocket
public class FastDbProjApplication implements WebSocketConfigurer {
    JsonProvider provider;
    MyWebSocketHandler handler;

    public static void main(String[] args) {
        SpringApplication.run(FastDbProjApplication.class, args);
        System.out.println();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/api/websocket").setAllowedOrigins("*");
    }


    @Bean("fileBasePath")
    public String fileContainer() {
        return "D:\\#__Coding_Projects\\Spring\\UPLOADED_DATA\\";
    }

    @Bean("exe")
    public Executor myExecutor() {
        return new SimpleAsyncTaskExecutor();
    }


    @Autowired
    public void setProvider(JsonProvider provider) {
        this.provider = provider;
    }

    @Autowired
    public void setHandler(MyWebSocketHandler handler) {
        this.handler = handler;
    }

}
