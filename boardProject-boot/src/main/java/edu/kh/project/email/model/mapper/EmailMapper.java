package edu.kh.project.email.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailMapper {

	/** 기존 이메일에 대한 인증키 수정
	 * @param map (email, authKey)
	 * @return int 행의 갯수
	 */
	int updateAuthKey(Map<String, String> map);

	/** 이메일과 인증번호 삽입
	 * @param map (email, authKey)
	 * @return int 행의 갯수
	 */
	int insertAuthKey(Map<String, String> map);

}
