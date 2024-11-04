package edu.kh.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.todo.model.dto.Todo;
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
 * @RequestBody
 * - 비동기 요청(ajax)시 전달되는 데이터 중
 *   body 부분에 포함된 요청 데이터를 
 *   알맞은 Java 객체 타입으로 바인딩하는 어노테이션
 *   
 *   
 * 
 * [HttpMessageConvertor]
 * Spring에서 비동기 통신 시
 * - 전달받은 데이터의 자료형
 * - 응답하는 데이터의 자료형
 * 위 두가지를 알맞은 형태로 가공(변환)해주는 객체
 * 
 *     Java                     JS
 * 문자열,숫자 <------------>  TEXT
 *      Map    <->  JSON  <->  JS Object
 * 		DTO    <->  JSON  <->  JS Object
 * 
 * JSON(JavaScript Object Notation)
 * 데이터를 표현하기 위한 경량 형식으로, 주로 키-값 쌍으로 이루어진 구조
 * 주로 서버와 클라이언트 간의 데이터 전송에 사용됨
 * 
 * 
 * (참고)
 * Spring에서 HttpMessageConverter 가 동작하기 위해서는
 * Jackson-data-bind 라이브러리가 필요한데
 * Spring Boot 모듈에 내장되어 있음
 * (Jackson : 자바에서 JSON 다루는 방법 제공하는 라이브러리)
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
	
	@ResponseBody
	@GetMapping("completeCount")
	public int getCompleteCount() {
		return service.getCompleteCount();
	}
	
	
	// 할 일 추가 
	
	@ResponseBody // 비동기 요청 결과로 값을 반환
	@PostMapping("add")
	public int addTodo(// @RequestParam 는 일반적으로 쿼리 파라미터나 URL 파라미터에 사용
					
				@RequestBody Todo todo // 요청 body에 담긴 값을 Todo DTO에 저장
				// @RequestBody는 기본적으로 JSON 형식을 기대함.
				// -> 요청보내는 곳에서 데이터를 JSON 형태로 제출해야함
				// -> ex) JSON.stringify(js객체)
			) {
		
		log.debug(todo.toString());
		// Todo(todoNo=0, todoTitle=ㅎㅇ, todoContent=hello, complete=null, regDate=null)
		
		// 할 일 추가하는 서비스 호출 후 응답 받기
		int result = service.addTodo(todo.getTodoTitle(), todo.getTodoContent());
		
		return result;
	}
	
	
	// 할 일 목록을 조회
	
	@ResponseBody
	@GetMapping("selectList")
	public List<Todo> selectList() {
		List<Todo> todoList = service.selectList();
		
		return todoList;
		
		// List(Java 전용 타입)를 반환
		// -> JS가 인식할 수 없기 때문에
		//  HttpMessageConverter가 JSON 형태로 변환하여 반환
	}
	
	// 할 일 상세 조회
	@ResponseBody 		// 비동기 요청한 곳으로 데이터 돌려보냄
	@GetMapping("detail")
	public Todo selectTodo(@RequestParam("todoNo") int todoNo) {
		return service.todoDetail(todoNo);
		// return 자료형 : Todo (DTO)
		// -> HttpMessageConverter가 String(JSON)형태로 변환해서 반환
	}
	
	// 할 일 삭제 요청 (DELETE 방식)
	@ResponseBody
	@DeleteMapping("delete")
	public int todoDelete(@RequestBody int todoNo) {
		return service.todoDelete(todoNo);
	}
	
	
	
	
	
	
	
	
}
