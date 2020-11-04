package kr.spring.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	//프로퍼티
	@Resource
	private MemberMapper memberMapper;
	
	//여러 작업을 작업 단위로 묶음. 트랜잭션 처리
	@Override
	public void insertMember(MemberVO member) {
		member.setMem_num(memberMapper.selectMem_num()); //1.
		memberMapper.insertMember(member); //2.
		memberMapper.insertMember_detail(member); //3.
		
		// 1~3 모두 성공해야 commit, 아니면 rollback
		//원자성 : 한 트랜잭션 내에서 실행한 작업들은 하나로 간주. 즉, 모두 성공 또는 모두 실패. 
	}

	@Override
	public MemberVO selectCheckMember(String id) {
		return memberMapper.selectCheckMember(id);
	}

	@Override
	public MemberVO selectMember(Integer mem_num) {
		return memberMapper.selectMember(mem_num);
	}

	@Override
	public void updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassword(MemberVO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMember(Integer mem_num) {
		// TODO Auto-generated method stub
		
	}

}
