package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 요청 / 응답 제어 역할 명시 + Bean(== 스프링이 만들고 관리하는 객체) 등록
public class ExampleController {
	/*
	 * 1) @RequestMapping("주소")
	 * 
	 * 2) @GetMapping("주소")     :   Get (조회) 방식 요청 매핑
	 * 
	 *    @PostMapping("주소")    :   Post (삽입) 방식 요청 매핑
	 *    
	 *    @PutMapping("주소")     :   Put (수정) 방식 요청 매핑 (form, a태그 요청 불가)
	 *    
	 *    @DeleteMapping("주소")  :   Delete (삭제) 방식 요청 매핑 (form, a태그 요청 불가)
	 * 
	 * 
	 * */
	
	/*
	 * Spring Boot에서는 요청 주소 앞에 "/" 가 없어도
	 * 요청 처리가 잘 되지만 
	 * 보통 "/" 작성 안하는 걸 권장.
	 * 
	 * -> 프로젝트 -> 사용자가 사용할 수 있게끔 인터넷상에 배포하는 일(최종 목표)
	 * -> 호스팅 서비스(AWS -> 프리티어 인스턴스 -> 리눅스 )
 	 * -> 리눅스에서 요청주소 앞에 "/" 있으면 빌드 중 에러..
	 * */
	
	@GetMapping("example")   //   /example  GET 방식 요청 매핑
	public String exampleMethod() {
		
		// forward 하려는 html 파일 경로 return 작성
		// 단, ViewResolver가 제공하는
		// Thymeleaf의 접두사, 접미사는 제외하고 작성
		
		// 접두사 : classpath:/templates/
		// 접미사 : .html
		return "example";
	}
	
	
	
	
	
	
	
	
	
	
	
}
