package com.packt.foodychoice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.packt.foodychoice.domain.MemberDTO;
import com.packt.foodychoice.domain.MemberRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {
	private MemberRepository mrepository;
	private PasswordEncoder bc;
	
	public String saveMember(MemberDTO dto) {
		dto.setUserpw(bc.encode(dto.getUserpw()));
		mrepository.save(dto.toMember());
		return dto.getUserid();
	}
}
