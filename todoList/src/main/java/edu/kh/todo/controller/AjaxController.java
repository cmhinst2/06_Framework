package edu.kh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.todo.model.service.TodoService;
import lombok.extern.slf4j.Slf4j;

/*
 * @ResponseBody
 * - 컨트롤러 메서드의 반환 값을
 *   Http 응답 본문에 직접 바인딩하는 역할임을 명시
 *   
 * - 컨트롤러 메서드의 반환 값을
 *   비동기 요청했던
 *   HTML/JS 파일 부분에 값을 돌려 보낼 것이다 를 명시
 *   
 *   -> forward/redirect 로 인식 X
 *  
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */

@Slf4j
@RequestMapping("ajax")
@Controller // 요청/응답 제어하는 역할 명시 + Bean 등록
public class AjaxController {
	
	// @Autowired 
	// - 등록된 bean 중 같은 타입 또는 상속관계인 Bean을
	//   해당 필드에 의존성 주입(DI)
	
	@Autowired
	private TodoService service;
	
	
	@GetMapping("main")   //   /ajax/main GET 요청 매핑
	public String ajaxMain() {
		// 접두사 : classpath:/templates/
		// 접미사 : .html
		return "ajax/main";
	}
	
	// 전체 Todo 개수 조회
	// -> forward / redirect 를 원하는게 아님!
	// -> "전체 Todo 개수" 라는 데이터가 비동기 요청 보낸쪽으로 반환되는 것을 원함!!!
	
	@ResponseBody // 반환값을 HTTP 응답 본문으로 직접 전송 (값 그대로 돌려보낼거야!!!)
	@GetMapping("totalCount")
	public int getTotalCount() {
		
		// 전체 할 일 개수 조회 서비스 호출
		int totalCount = service.getTotalCount();
		
		return totalCount;
	}
	
	
	
	
	
	
	
	
	
	
	
}
