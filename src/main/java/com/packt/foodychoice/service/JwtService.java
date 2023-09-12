package com.packt.foodychoice.service;

import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	//토큰 만료 시간 1일을 밀리초로 변경
	static final long EXPIRATIONTIME = 86400000;
	//토큰의 접두사 지정
	static final String PREFIX = "Bearer";
	//비밀키 생성
	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	//토큰 생성
	public String getToken(String userid) {
		String token = Jwts.builder()
							.setSubject(userid)
							.setExpiration(new Date(System.currentTimeMillis()
									+ EXPIRATIONTIME))
							.signWith(key) 	// 서명
							.compact();		// 압축
		return token;
	}
	
	//요청 권한 부여 헤더에서 토큰을 가져와 토큰을 확인하고 사용자 이름을 리턴
	public String getAuthUser(HttpServletRequest req) {
		//헤더에서 토큰 반환
		String token = req.getHeader(HttpHeaders.AUTHORIZATION);
		if(token!=null) {
			String user = Jwts.parserBuilder()
							.setSigningKey(key)
							.build()
							.parseClaimsJws(token.replace(PREFIX, ""))
							.getBody()
							.getSubject();
			if(user != null) {
				return user;
			}
		}
		return null;
	}
}
