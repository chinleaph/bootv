package com.bootx.bootv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.bootx.bootv.dao")
public class BootvApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootvApplication.class, args);
	}
}
