package com.packt.foodychoice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.packt.foodychoice.domain.MemberDTO;
import com.packt.foodychoice.service.MemberService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MemberController {
	private final MemberService service;
	
	//회원가입
	@PostMapping("/member/join")
	public String saveMember(@RequestBody MemberDTO dto) {
		return service.saveMember(dto);
	}
}
