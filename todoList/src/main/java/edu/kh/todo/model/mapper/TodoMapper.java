package edu.kh.todo.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todo.model.dto.Todo;

/*
 * @Mapper
 * - MyBatis 제공 어노테이션
 * 
 * - MyBatis가 해당 인터페이스를 DAO로 인식하여 SQL 매핑을 처리
 * 
 * - 해당 어노테이션이 작성된 인터페이스는
 *   namespace에 해당 인터페이스가 작성된
 *   mapper.xml 파일과 연결되어 SQL 호출/수행/결과 반환 가능
 * 
 * - Mybatis에서 제공하는 Mapper 상속 객체가 Bean으로 등록됨 
 * 
 * - @Mapper를 사용할 때 @Repository 는 필요하지 않음.
 *   -> Mybatis가 DAO 객체로 Mapper 인터페이스를 관리하기 때문. 
 * */



@Mapper
public interface TodoMapper {
	
	/* Mapper의 메서드명 == mapper.xml 파일 내 태그의 id
	 * 
	 * (메서드명과 id가 같은 태그가 서로 연결된다)
	 * 
	 * */

	/** (TEST) todoNo가 1인 할 일 제목 조회
	 * @return
	 */
	String testTitle();

	/** 할 일 목록 조회
	 * @return todoList
	 */
	List<Todo> selectAll();

	/** 완료된 할 일 개수 조회
	 * @return completeCount
	 */
	int getCompleteCount();

	/** 할 일 추가
	 * @param todo
	 * @return result
	 */
	int addTodo(Todo todo);

	/** 할 일 상세 조회
	 * @param todoNo
	 * @return
	 */
	Todo todoDetail(int todoNo);

	/** 완료 여부 변경
	 * @param todo
	 * @return result
	 */
	int changeComplete(Todo todo);

	

	/** 할 일 삭제
	 * @param todoNo
	 * @return result
	 */
	int todoDelete(int todoNo);

	/** 할 일 수정
	 * @param todo
	 * @return result
	 */
	int todoUpdate(Todo todo);

	/** 전체 할 일 개수 조회
	 * @return totalCount
	 */
	int getTotalCount();
}











