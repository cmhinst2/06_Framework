package edu.kh.project.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.email.model.service.EmailService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("email")
@RequiredArgsConstructor // final 필드에 자동으로 의존성 주입 (@Autowired 생성자 방식 코드 자동완성)
public class EmailController {

	private final EmailService service;
	
	@ResponseBody
	@PostMapping("signup")
	public int signup(@RequestBody String email) {
		
		String authKey = service.sendEmail("signup", email);
		
		if(authKey != null) { // 인증번호가 반환되어 돌아옴 
							// == 이메일 보내기 성공
			return 1;
		}
		
		// 이메일 보내기 실패
		return 0;
		
	}
	
	
	
	
	
	
	
	
	
	
}
