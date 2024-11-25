package edu.kh.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 스프링 스케줄러를 이용하기 위한 활성화 어노테이션
@SpringBootApplication(exclude={SecurityAutoConfiguration.class}) // Spring Security에서 기본 제공하는 로그인 페이지를 이용 안하겠다!
public class BoardProjectBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardProjectBootApplication.class, args);
	}

}
