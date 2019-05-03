package com.everis.jirainfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan(basePackages = { "com.everis.jirainfo.entity" })
//@EnableJpaRepositories(basePackages = { "com.everis.jirainfo.repository" })
//@ComponentScan(basePackages = {"com.everis.jirainfo.controller"})
public class JirainfoApplication {
	public static void main(String[] args) {
		SpringApplication.run(JirainfoApplication.class, args);
	}
	
}
