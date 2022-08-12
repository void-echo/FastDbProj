package com.echo.fastdbproj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
}
