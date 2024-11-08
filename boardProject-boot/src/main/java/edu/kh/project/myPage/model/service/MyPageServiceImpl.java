package edu.kh.project.myPage.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class) // 모든 예외 발생 시 롤백 / 아니면 커밋
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService{

	private final MyPageMapper mapper;
	
	// 회원 정보 수정
	@Override
	public int updateInfo(Member inputMember, String[] memberAddress) {
		
		// 입력된 주소가 있을 경우
		// memberAddress를 A^^^B^^^C 형태로 가공
		
		// 주소 입력 X -> inputMember.getMemberAddress() -> ",,"
		if(inputMember.getMemberAddress().equals(",,")) {
			
			// 주소에 null 대입
			inputMember.setMemberAddress(null);
			
		} else { // 주소 입력 O
			
			String address = String.join("^^^", memberAddress);
			inputMember.setMemberAddress(address);
			
		}
		
		
		return mapper.updateInfo(inputMember);
	}
	
	
	
	
	
	
	
	
}
