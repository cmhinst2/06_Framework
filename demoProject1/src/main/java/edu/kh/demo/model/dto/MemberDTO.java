package edu.kh.demo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Lombok 라이브러리 이용

@Getter 			// getter
@Setter				// setter 
@ToString			// toString 오버라이딩
@NoArgsConstructor // 기본생성자
public class MemberDTO {
	
	private String memberId;
	private String memberPw;
	private String memberName;
	private int	   memberAge;
}
