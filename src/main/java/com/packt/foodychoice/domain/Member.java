package com.packt.foodychoice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="`member`")
@NoArgsConstructor
public class Member {
	@Id
	private String userid;
	@Column(nullable = false)
	private String userpw;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String role;
	
	@Builder
	public Member(String userid, String userpw, String email, String role) {
		this.userid = userid;
		this.userpw = userpw;
		this.email = email;
		this.role = role;
	}

}
