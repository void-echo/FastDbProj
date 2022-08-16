package com.echo.fastdbproj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@MapperScan("com.echo.fastdbproj.dao")
public class FastDbProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastDbProjApplication.class, args);
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
}
