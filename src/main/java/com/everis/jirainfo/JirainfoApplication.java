package com.everis.jirainfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EntityScan(basePackages = { "com.everis.jirainfo.entity" })
//@EnableJpaRepositories(basePackages = { "com.everis.jirainfo.repository" })
//@ComponentScan(basePackages = {"com.everis.jirainfo.controller"})
public class JirainfoApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(JirainfoApplication.class, args);
	}
	
}
