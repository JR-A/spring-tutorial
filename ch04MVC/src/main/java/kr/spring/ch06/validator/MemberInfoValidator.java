package kr.spring.ch06.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.spring.ch06.model.MemberInfo;

public class MemberInfoValidator implements Validator {

	//Validator가 검증할 수 있는 타입인지(자바빈 구조인지) 여부를 리턴
	@Override
	public boolean supports(Class<?> clazz) {

		return MemberInfo.class.isAssignableFrom(clazz);
	}
	
	//target 객체에 대한 검증 실행(유효성 체크). 검증 결과 문제가 있을 경우 errors 객체에 어떤 문제인지에 대한 정보 저장
	@Override
	public void validate(Object target, Errors errors) {
		MemberInfo memberInfo = (MemberInfo)target; //Object->MemberInfo 다운캐스팅
		
		//trim() : 문자열의 앞 뒤 공백 제거(문자열 중간의 공백은 제거하지 않음)
		//null이거나 빈 문자열 전송시
		if(memberInfo.getId() == null || memberInfo.getId().trim().isEmpty()) {
			 			//자바빈의field, error code(validation.properties에 저장된)
			errors.rejectValue("id", "required"); 
		}
		if(memberInfo.getName() == null || memberInfo.getName().trim().isEmpty()) {
			errors.rejectValue("name", "required");
		}
		if(memberInfo.getZipcode() == null || memberInfo.getZipcode().trim().isEmpty()) {
			errors.rejectValue("zipcode", "required");
		}
		if(memberInfo.getAddress1() == null || memberInfo.getAddress1().trim().isEmpty()) {
			errors.rejectValue("address1", "required");
		}
		if(memberInfo.getAddress2() == null || memberInfo.getAddress2().trim().isEmpty()) {
			errors.rejectValue("address2", "required");
		}
	}

}
