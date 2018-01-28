package com.base.superAdmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
		basePackages = {"util.security", "util.microservice", "util.initialLoader"})
public class SuperAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperAdminApplication.class, args);
	}
}
