package com.twitter.interview.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.twitter.interview.utils", "com.twitter.interview.controller",
		"com.twitter.interview.model", "com.twitter.interview.exception", "com.twitter.interview.repository"})
@EntityScan(basePackages = {"com.twitter.interview.entity"})
@EnableJpaRepositories(basePackages = {"com.twitter.interview.repository"})
public class TwitterWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterWebServicesApplication.class, args);
	}

}
