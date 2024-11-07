package edu.kh.project.common.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:/config.properties")
public class EmailConfig {
	
	/*
	 * 
	 	SMTP (Simple Mail Transfer Protocol): 이메일을 전송하는 데 사용되는 표준 통신 프로토콜.
	 *	-> 이메일 클라이언트가 이메일을 작성하여 전송할 때, SMTP 서버를 통해 수신자의 이메일 서버로 메일을 전달하는 역할
	 * 	-> 기본적으로 포트 25를 사용하지만, 보안을 강화한 포트 587이나 포트 465(SSL/TLS)가 자주 사용됨
	 * 
	 * 동작방식 
	 * 
	1. Spring Boot 애플리케이션에서 내가 이메일을 보냄
	2. Google SMTP 서버로 전송 (Google 서버가 "중간 배달자" 역할을 하는 셈)
	3. Google SMTP 서버가 이메일 주소에 맞는 최종 수신자의 이메일 서버(예: Gmail, Yahoo, 회사 이메일 서버)로 이메일을 다시 보냄.
	4. 수신자가 이메일을 받음
	 * 
	 * 
	 * (참고)
	 * HTTP (Hypertext Transfer Protocol): 웹 브라우저와 서버 간에 웹 페이지나 파일을 주고받는 데 사용
	 * 
	 * 전송 방향
	 * - HTTP: 클라이언트-서버 간 통신을 기반으로 하며, 클라이언트가 서버에 요청(Request)을 보내고 서버가 응답(Response).
	   - SMTP: 서버 간 통신이 가능하며, 이메일을 발신 서버에서 수신 서버로 전달하는 식으로 동작함.
	 * */

	// @Value : properties에 작성된 내용 중 key가 일치하는 value값을 얻어와 필드에 대입
	@Value("${spring.mail.username}")
	private String userName;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Bean
	public JavaMailSender javaMailSender() {
		/*
		 * 
		 * Spring에서 JavaMailSender를 구성하는 Bean을 정의.
		 * 
		 * JavaMailSender : 이메일을 보내는데 사용되는 인터페이스로,
		 * 	JavaMailSenderImpl 클래스를 통해 구현됨.
		 * SMTP 서버를 사용하여 이메일을 보내기 위한 구성을 제공.
		 * 
		 * 
		 * */
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		Properties prop = new Properties();
		
		prop.setProperty("mail.transport.protocol", "smtp"); // 전송 프로토콜을 설정. 여기서는 SMTP를 사용
		prop.setProperty("mail.smtp.auth", "true"); // SMTP 서버 인증을 사용할지 여부를 설정함. 
													// true로 설정되어 있으므로 인증이 사용됨
													// SMTP 서버를 사용하여 이메일을 보내려면 보안 상의 이유로 인증이 필요.
													// (사용자이름(이메일)과 비밀번호(앱비밀번호) 확인)
		prop.setProperty("mail.smtp.starttls.enable", "true"); // STARTTLS를 사용하여 안전한 연결을 활성화할지 여부를 설정
		prop.setProperty("mail.debug", "true"); 		// 디버그 모드를 설정
		prop.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com"); // 신뢰할 수 있는 SMTP 서버 호스트를 지정
		prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2"); // SSL 프로토콜을 설정. 여기서는 TLSv1.2를 사용
		
		
		mailSender.setUsername(userName); // 이메일 계정의 사용자 
		mailSender.setPassword(password); // 이메일 계정의 비밀번호
		mailSender.setHost("smtp.gmail.com"); // SMTP 서버 호스트를 설정.  Gmail의 SMTP 서버인 "smtp.gmail.com"을 사용
		mailSender.setPort(587); 			// SMTP 서버의 포트 587로 설정
		mailSender.setDefaultEncoding("UTF-8"); // 기본 인코딩을 설정
		mailSender.setJavaMailProperties(prop); // JavaMail의 속성을 설정(앞서 정의해둔 prop 있는 설정들을 여기에 추가)
		
		return mailSender; 
		// 위처럼 각종 설정이 적용된 JavaMailSender를 Bean으로 등록하여 
		//Spring 애플케이션에서 이메일을 보내기 위한 구성을 제공함
		
	}
	
	
	
	
	
	
}
