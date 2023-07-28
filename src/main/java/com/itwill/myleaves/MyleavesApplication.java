package com.itwill.myleaves;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing 
@SpringBootApplication
public class MyleavesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyleavesApplication.class, args);
	}

}
