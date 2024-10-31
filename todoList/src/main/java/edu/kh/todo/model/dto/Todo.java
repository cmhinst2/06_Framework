package edu.kh.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
	private int    todoNo; 		// 할 일 번호
	private String todoTitle; 	// 할 일 제목
	private String todoContent; // 할 일 내용
	private String complete; 	// 할 일 완료 여부 ("Y", "N")
	private String regDate;  	// 할 일 등록일
}
