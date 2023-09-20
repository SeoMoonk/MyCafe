package com.seomoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MycafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycafeApplication.class, args);
	}

}
