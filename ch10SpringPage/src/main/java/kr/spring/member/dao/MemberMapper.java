package kr.spring.member.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.member.vo.MemberVO;

//하나의 메서드에 하나의 sql문만 처리
public interface MemberMapper {
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num();
	@Insert("INSERT INTO spmember (mem_num, id) VALUES (#{mem_num}, #{id})")
	public void insertMember(MemberVO member);
	@Insert("INSERT INTO spmember_detail (mem_num,name,passwd,phone,email,zipcode,address1,address2) VALUES (#{mem_num},#{name},#{passwd},#{phone},#{email},#{zipcode},#{address1},#{address2})")
	public void insertMember_detail(MemberVO member);
	public MemberVO selectCheckMember(String id);
	public MemberVO selectMember(Integer mem_num);
	public void updateMember(MemberVO member);
	public void updatePassword(MemberVO member);
	public void deleteMember(Integer mem_num);
	public void deleteMember_detail(Integer mem_num);
}
