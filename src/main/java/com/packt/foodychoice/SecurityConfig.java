package com.packt.foodychoice;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.packt.foodychoice.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = 
				new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		//출처를 명시적으로 정의 하려면 출처주소를 명시해주면 된다.
		//localhost:3000 허용
		//* 모든 주소 허용
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(false);
		source.registerCorsConfiguration("/**",config);
		return source;
	}
	//인증 필터 의존 주입
	@Autowired
	private AuthenticationFilter authenticationFilter;
		
	//AuthenticationManagerfmf LoginController클래스에 주입했으므로
	//빈으로 등록해준다
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return authenticationManager();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
		
	//예외처리 의존 주입
	@Autowired
	private AuthEntryPoint exceptionHandler;
	
	@Override
	//보호되는 경로와 보호되지 않는 경로를 정의
	//csrf 비활성화
	protected void configure(HttpSecurity http) throws Exception {
		//전체를 보호되지 않는 경로로 변경
//		http.csrf().disable().cors().and()
//		.authorizeRequests().anyRequest().permitAll();
		
		http.csrf().disable().cors().and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		//login 엔드포인트에 대한 요청은 보호하지 않음
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers(HttpMethod.POST, "/member/join").permitAll()
		//다른 요청은 보호됨
		.anyRequest().authenticated().and()
		.exceptionHandling()
		.authenticationEntryPoint(exceptionHandler).and()
		.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	
	}	

}
