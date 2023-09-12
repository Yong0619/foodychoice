package com.packt.foodychoice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	//예외를 매개변수로 받는 메소드 구현
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		//응답상태는 401로 설정
		//SC_UNAUTHORIZED : 401로 지정하는 코드
		//SC_NOT_FOUND : 404로 지정
		//SC_FORBIDDEN : 403으로 지정
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		PrintWriter write = response.getWriter();
		write.println("Error : "+authException.getMessage());
		
	}
	
}
