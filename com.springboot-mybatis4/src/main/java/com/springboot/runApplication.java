package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基础SpringBoot，使用Restful风格的API实现CRUD操作
 * @author Evan
 *
 */
@SpringBootApplication
public class runApplication {
	public static void main(String[] args) {
		SpringApplication.run(runApplication.class, args);
	}
}
