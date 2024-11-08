package edu.kh.test.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.test.member.dto.Customer;
import edu.kh.test.member.service.CustomerService;

@Controller
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	// Model : 값 전달용 객체 (request scope 기본) 
	// 서버 (controller) -> 클라이언트(html) 

	@PostMapping("updateCustomer")
	public String updateCustomer(Customer customer, Model model) {
		int result = service.updateCustomer(customer);
		if (result > 0)
			model.addAttribute("message", "수정 성공!!!");
		else
			model.addAttribute("message", "회원 번호가 일치하는 회원이 없습니다");
		return "result";
	}
	
	
	
	
	
	
}
