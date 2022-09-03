package com.zhao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication()
@MapperScan("com.zhao.module.system.mapper")
public class ServerApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext appCtx = SpringApplication.run(ServerApplication.class, args);
	}

}
