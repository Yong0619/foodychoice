package com.packt.foodychoice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.packt.foodychoice.domain.Member;
import com.packt.foodychoice.domain.MemberRepository;

@Configuration
@EnableWebSecurity
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private MemberRepository repository;

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		Optional<Member> user = repository.findByUserid(userid);
		UserBuilder builder = null;
		
		if(user.isPresent()) {
			Member currentUser = user.get();
			builder = org.springframework.security.core.userdetails.
					User.withUsername(userid);
			builder.password(currentUser.getUserpw());
			builder.roles(currentUser.getRole());
		} else {
			throw new UsernameNotFoundException("user not fount");
		}
		return builder.build();


	}
	
	
}
