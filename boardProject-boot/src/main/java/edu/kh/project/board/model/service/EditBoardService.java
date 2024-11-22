package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.dto.Board;

public interface EditBoardService {

	/** 게시글 작성
	 * @param inputBoard
	 * @param images
	 * @return boardNo
	 */
	int boardInsert(Board inputBoard, List<MultipartFile> images) throws Exception;

	/** 게시글 수정
	 * @param inputBoard
	 * @param images
	 * @param deleteOrderList
	 * @return
	 * @throws Exception
	 */
	int boardUpdate(Board inputBoard, List<MultipartFile> images, String deleteOrderList) throws Exception;

	/** 게시글 삭제
	 * @param map
	 * @return result
	 */
	int boardDelete(Map<String, Integer> map);

}
