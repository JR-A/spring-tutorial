package kr.spring.ch08.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kr.spring.ch08.model.SubmitReportVO;

public class SubmitReportValidator implements Validator {
	
	//Validator가 검증할 수 있는 타입인지(자바빈 구조인지) 여부를 리턴
	@Override
	public boolean supports(Class<?> clazz) {
		
		return SubmitReportVO.class.isAssignableFrom(clazz);
	}
	
	//target 객체에 대한 검증 실행(유효성 체크). 검증 결과 문제가 있을 경우 errors 객체에 어떤 문제인지에 대한 정보 저장
	@Override
	public void validate(Object target, Errors errors) {
		//문자열 유효성 체크 (빈 문자열 혹은 공백 전송시)		  Errors	필드		   에러코드
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "required");
		
		//파일 검사
		SubmitReportVO command = (SubmitReportVO)target;
		//파일이 null이거나 비어있는 경우
		if(command.getReportFile() == null || command.getReportFile().isEmpty()) {
			errors.rejectValue("reportFile", "required");
		}
	}

}
