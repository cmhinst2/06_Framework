package edu.kh.project.board.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.project.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("board")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;
	
	
	/** 게시글 목록 조회
	 * @param boardCode : 게시판 종류 구분 번호 (1/2/3..)
	 * @param cp : 현재 조회 요청한 페이지 번호 (없으면 1)
	 * @return 
	 * 
	 * {boardCode}
	 * 
	 *- /board/xxx
	 *  /board 이하 1레벨 자리에 어떤 주소값이 들어오든 모두 이 메서드 매핑
	 * 
	 *  /board 이하 1레벨 자리에 숫자로된 요청 주소가 작성되어 있을 때만 동작 -> 정규표현식 이용
	 *  
	 *  [0-9] : 한 칸에 0~9 사이 숫자 입력 가능
	 *  + : 하나 이상
	 *  
	 *  [0-9]+ : 모든 숫자
	 * 
	 */
	@GetMapping("{boardCode:[0-9]+}")
	public String selectBoardList(@PathVariable("boardCode") int boardCode,
								 @RequestParam(value="cp", required = false, defaultValue = "1") int cp,
								 Model model ) {
		
		// 조회 서비스 호출 후 결과 반환
		Map<String, Object> map = null;
		
		// 게시글 목록 조회 서비스 호출
		map = service.selectBoardList(boardCode, cp);
		
		// model에 반환 받은 값을 등록 
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));
		
		
		// forward : boardList.html 
		return "board/boardList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
