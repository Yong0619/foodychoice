package com.packt.foodychoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FoodychoiceApplication {
	//로그 사용위해 클래스 추가
	private static final Logger logger = LoggerFactory.getLogger(FoodychoiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FoodychoiceApplication.class, args);
		logger.info("애플리케이션 시작");
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
