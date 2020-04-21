package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan("com.dao")
public class GraduationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraduationProjectApplication.class, args);
	}

}
