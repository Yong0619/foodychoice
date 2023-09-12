package com.packt.foodychoice.domain;

import lombok.Data;

//테이블에 영향을 주지 않으면서 controller 와 view 페이지의 통신을 위해 DTO를 별도로 생성함
@Data
public class MemberDTO {
	private String userid;
	private String userpw;
	private String email;
	private String role;
	
	public Member toMember() {
		return Member.builder()
				.userid(userid)
				.userpw(userpw)
				.email(email)
				.role(role)
				.build();
	}
}
