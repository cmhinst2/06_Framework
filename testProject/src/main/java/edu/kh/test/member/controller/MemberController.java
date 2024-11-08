package edu.kh.test.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.test.member.dto.Member;
import jakarta.servlet.http.HttpServletRequest;

@Controller // 요청 + 응답 제어하는 역할 명시 + Bean 등록
//@RequestMapping("member")
public class MemberController {
	// @ModelAttribute : DTO 타입일때 
	// Model 

	@PostMapping("/member/select")
	public String selectMember(HttpServletRequest req, @ModelAttribute Member member) {
		req.setAttribute("memberName", member.getMemberName());
		req.setAttribute("memberAge", member.getMemberAge());
		req.setAttribute("memberAddress", member.getMemberAddress());

		// 컨트롤러의 메서드는 보통 반환형이 String
		// -> return 에 작성하는 문자열 forward 경로 or redirect 요청주소
		return "member/select";
	}
	
	
	
	
	
	
	
}
