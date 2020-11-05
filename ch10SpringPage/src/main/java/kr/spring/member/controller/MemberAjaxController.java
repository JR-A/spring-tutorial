package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;

@Controller
public class MemberAjaxController {
	
	//프로퍼티
	@Resource
	private MemberService memberService;
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	//자바빈(VO) 초기화후 request에 등록
	
	//아이디 중복체크
	@RequestMapping("/member/confirmId.do")
	@ResponseBody 	//-> 반환되는 map, list객체를 JSON문자열로 만들고 view 자동 생성 - view만들 필요가 없다  (전제조건:pom.xml에 JSON생성해주는 jackson 라이브러리 디펜던시 등록)
	public Map<String, String> process(@RequestParam("id") String id){
		//로그처리
		if(log.isDebugEnabled()) {
			log.debug("<<아이디 중복체크>> : " + id);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		
		MemberVO member = memberService.selectCheckMember(id);
		if(member != null) {
			//아이디 중복인 경우 (해당 아이디의 회원정보가 존재하는경우)
			map.put("result", "idDuplicated");
		}else {
			//아이디 미중복인 경우
			map.put("result", "idNotFound");
		}
		
		//http://localhost:8080/ch10SpringPage/member/confirmId.do?id=kim2 GET방식으로 JSON 제대로 나오는지 테스트해보기
		
		return map;
	}
}
