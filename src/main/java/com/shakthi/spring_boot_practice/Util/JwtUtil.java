package com.shakthi.spring_boot_practice.Util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET = "hello baby ,i love you will you marry me ";
	private final long EXPIRATION = 1000 * 60 ;
	private final Key secrectKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)); 
	
	public String generateToken(String email) {
		
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(secrectKey,SignatureAlgorithm.HS256)
				.compact();
		}
	
		public String extractEmailToken(String Token) {
			return Jwts.parserBuilder()
			.setSigningKey(secrectKey)
			.build()
			.parseClaimsJws(Token)
			.getBody()
			.getSubject();
		}
	
		public boolean validateToken(String Token) {
				
			try {
			extractEmailToken(Token);
				return true;	
			}catch(JwtException e) {
				return false;
			}
			
		}
}
