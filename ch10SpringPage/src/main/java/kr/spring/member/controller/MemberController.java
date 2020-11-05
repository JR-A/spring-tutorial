package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.LoginCheckException;

@Controller
public class MemberController {
	
	//프로퍼티
	@Resource
	private MemberService memberService;
	
	//로그 처리(로그 대상 지정)
	private Logger log = Logger.getLogger(this.getClass());
	
	//자바빈(VO) 초기화후 request에 등록
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	 
	//회원 가입 폼 - GET방식으로 전송시
	@RequestMapping(value="/member/registerUser.do", method=RequestMethod.GET)
	public String form() {
		return "memberRegister"; //jsp 호출이 아닌 definition name 호출 (member.xml에 저장된 모듈화된 페이지)
	}
	
	//회원 가입 처리 - POST방식으로 전송시
	@RequestMapping(value="/member/registerUser.do", method=RequestMethod.POST)
	public String submit(@Valid MemberVO memberVO, BindingResult result) 
	{
		//전송된 데이터 유효성 체크 -> @Valid 어노테이션으로 체크
		
		//로그 처리
		if(log.isDebugEnabled()) {
			log.debug("<<회원 가입>> : " + memberVO);
		}
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 */
		if(result.hasErrors()) {
			return form();
		}
		
		//에러가 없으면 회원 가입 처리
		memberService.insertMember(memberVO);
		
		return "redirect:/main/main.do";	//회원가입 완료 창 보여주고싶으면 View만들어야함
	}
	
	//로그인 폼 - GET방식으로  전송시
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String formLogin() {
		return "memberLogin"; //jsp 호출이 아닌 definition name 호출 (member.xml에 저장된 모듈화된 페이지)
	}
	
	//로그인 처리 - POST방식으로 전송시
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String submitLogin(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {	//Dispatcher에서 session 받아오기
		
		//전송된 데이터 유효성 체크 -> @Valid 어노테이션으로 체크
		
		//로그 처리
		if(log.isDebugEnabled()) {
			log.debug("<<MemberVO>> : " + memberVO);
		}
				
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 * id와 passwd 필드만 체크!
		 */
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLogin();
		}
		
		//로그인 체크(id와 비밀번호가 일치하는지 체크)
		try {
			MemberVO member = memberService.selectCheckMember(memberVO.getId());
			boolean check = false;

			if(member!=null) {
				//비밀번호 일치여부 체크
				check = member.isCheckedPasswd(memberVO.getPasswd());
			}
			if(check) {
				//인증 성공, 로그인 처리
				session.setAttribute("user", member);
				
				return "redirect:/main/main.do";
			} else {
				//인증 실패
				throw new LoginCheckException();
			}
		}catch(LoginCheckException e) {
			//인증 실패
			result.reject("invalidIdOrPassword");
			
			return formLogin();
		}
	}
	
	
	//로그아웃 처리
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session) {
		//로그아웃
		session.invalidate(); //세션 무효화
		
		return "redirect:/main/main.do";
	}
	
	//마이페이지(회원 상세 정보)
	@RequestMapping("/member/myPage.do")
	public String process(HttpSession session, Model model) {	//mem_num가져오기 위한 session, 데이터 전달 위한 model
		
		//세션에 저장된 회원 정보 반환 -> 회원 번호 얻기위함
		MemberVO vo = (MemberVO)session.getAttribute("user");
		
		//회원 정보
		MemberVO member = memberService.selectMember(vo.getMem_num());
		
		//로그처리
		if(log.isDebugEnabled()) {
			log.debug("<<회원 상세 정보>> : " + member);
		}
		
		model.addAttribute("member", member);	//model이용해 데이터를 request에 저장
		
		return "memberView";	//definition name 호출(member.xml에 저장된 모듈화된 페이지)
	}
	
	//회원 정보 수정 폼 - GET방식으로 전송시
	@RequestMapping(value="/member/update.do", method=RequestMethod.GET)
	public String formUpdate(HttpSession session, Model model) {	//mem_num가져오기 위한 session, 데이터 전달 위한 model
		
		//세션에 저장된 회원 정보 반환 -> 회원 번호 얻기위함
		MemberVO vo = (MemberVO)session.getAttribute("user");
		
		//회원 정보
		MemberVO member = memberService.selectMember(vo.getMem_num());
		
		model.addAttribute("member", member);	//model이용해 데이터를 request에 저장
		
		return "memberModify";
	}
	
	//회원 정보 수정 처리 - POST방식으로 전송시
	@RequestMapping(value="/member/update.do", method=RequestMethod.POST)
	public String submitUpdate(@Valid MemberVO memberVO, BindingResult result, HttpSession session) { //mem_num가져오기 위한 session
		
		//전송된 데이터 유효성 체크 -> @Valid 어노테이션으로 체크
		
		//로그 처리
		if(log.isDebugEnabled()) {
			log.debug("<<회원 정보 수정>> : " + memberVO);
		}
		
		//BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		if(result.hasErrors()) {
			return "memberModify";
		}
		
		//세션에 저장된 회원 정보 반환 -> 회원 번호 얻기위함
		MemberVO vo = (MemberVO)session.getAttribute("user");
		//전송된 데이터가 저장된 자바빈에 회원 번호를 저장
		memberVO.setMem_num(vo.getMem_num());
		
		//회원 정보 수정
		memberService.updateMember(memberVO);
		
		return "redirect:/member/myPage.do";
	}
	
	//비밀번호 변경 폼 - GET방식으로 전송시
	@RequestMapping(value="/member/changePassword.do", method=RequestMethod.GET)
	public String formChangePassword() {
		
		return "memberChangePassword";
	}
	
	//비밀번호 변경 - POST방식으로 전송시
	@RequestMapping(value="/member/changePassword.do", method=RequestMethod.POST)
	public String submitChangePassword(@Valid MemberVO memberVO, BindingResult result, HttpSession session) { //mem_num가져오기 위한 session
		
		//전송된 데이터 유효성 체크 -> 폼에서 자바스크립트로, 서버단에서 @Valid 어노테이션으로 체크
		
		//로그 처리
		if(log.isDebugEnabled()) {
			log.debug("<<비밀번호 변경 처리>> : " + memberVO);
		}
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 * now_passwd, passwd 필드 전송됐는지 여부 체크
		 */
		if(result.hasFieldErrors("now_passwd") || result.hasFieldErrors("passwd")) {
			return "memberChangePassword";	
		}
		
		//세션에 저장된 회원 정보 반환 -> 회원 번호 얻기위함
		MemberVO vo = (MemberVO)session.getAttribute("user");
		//현재 비밀번호(now_passwd)와 변경할 비밀번호(passwd)가 저장된 자바빈에 회원 번호 저장
		memberVO.setMem_num(vo.getMem_num());
		
		//비밀번호 일치 여부 체크
		//회원 번호로 DB에서 회원정보 읽어오기
		MemberVO member = memberService.selectMember(memberVO.getMem_num());
		
		//입력한 현재 비밀번호 DB에서 읽어온 비밀번호 일치 여부 체크
		if(!member.getPasswd().equals(memberVO.getNow_passwd())) { //일치하지 않으면 result에 오류 저장후 form 호출
								//필드		에러코드
			result.rejectValue("now_passwd", "invalidPassword");
			
			return "memberChangePassword";
		}
		
		//비밀번호 변경
		memberService.updatePassword(memberVO);
		
		return "redirect:/member/myPage.do";
	}
	
	//회원 탈퇴 폼 - GET방식으로 전송시
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
	public String formDelete() {
		
		return "memberDelete";
	}
	
	//회원 탈퇴 처리 - POST방식으로 전송시
	@RequestMapping(value="/member/delete.do", method=RequestMethod.POST)
	public String submitDelete(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		
		//전송된 데이터 유효성 체크 -> @Valid 어노테이션으로 체크
		
		//로그처리
		if(log.isDebugEnabled()) {
			log.debug("<<회원탈퇴>> : " + memberVO);
		}
		
		/*
		 * BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장돼있으면 form을 호출
		 * id, passwd 필드 전송됐는지 여부 체크
		 */
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return "memberDelete";
		}
		
		//세션에 저장된 회원 정보 반환 -> 회원 번호 얻기위함
		MemberVO vo = (MemberVO)session.getAttribute("user");
		//전송된 아이디(id)와 비밀번호(passwd)가 저장된 자바빈에 회원 번호 저장
		memberVO.setMem_num(vo.getMem_num());
		
		//회원 번호로 DB에서 회원정보 읽어오기
		MemberVO member = memberService.selectMember(memberVO.getMem_num());
		
		//비밀번호 일치여부 체크
		boolean check = false;
		if(member!=null && memberVO.getId().equals(vo.getId())) {	//회원 정보가 존재하고, 전송된 아이디와 현재 세션의 아이디가 일치하는지 체크
			check = member.isCheckedPasswd(memberVO.getPasswd()); //전송된 비밀번호와 DB에서 가져온 회원의 비밀번호 일치 여부 체크
		}
		
		if(check) {
			//인증 성공, 회원정보 삭제
			memberService.deleteMember(memberVO.getMem_num());
			//로그아웃
			session.invalidate();
			
			return "redirect:/main/main.do";
		}else {
			//인증 실패
			result.reject("invalidIdOrPassword");
			
			return "memberDelete";
		}
		
	}
	
	//프로필 이미지 출력 - header.jsp, memberView.jsp에서 호출
	@RequestMapping("/member/photoView.do")
	public ModelAndView viewImage(HttpSession session) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		MemberVO memberVO = memberService.selectMember(user.getMem_num());
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름 설정
		mav.setViewName("imageView");//파일 다운로드시 사용하는 viewResolver에 의해, mav를 반환하면 dispatcherServlet이 같은 beanName가진 bean호출
		//데이터 저장
		mav.addObject("imageFile", memberVO.getPhoto());
		mav.addObject("filename", memberVO.getPhotoname());
		
		return mav;
	}
}
