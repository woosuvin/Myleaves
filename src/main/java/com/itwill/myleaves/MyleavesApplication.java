package com.itwill.myleaves;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@EnableJpaAuditing 
@SpringBootApplication
public class MyleavesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyleavesApplication.class, args);
		log.info("main started");
	}

}
