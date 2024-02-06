package com.project.elearning.jwtservice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	public static final String SECRET_KEY = "secret";

	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}

	public Date extractExpirationDate(String token) {
		return extractAllClaims(token).getExpiration();
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(String token) {
		return extractExpirationDate(token).before(new Date());
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		String username = userDetails.getUsername();
		String extractedUsername = extractUsername(token);

		return (username.equals(extractedUsername) && (!isTokenExpired(token)));
	}

	public String generateToken(UserDetails userDetails) {

		Map<String, Object> map = new HashMap<>();

		return Jwts.builder().setClaims(map).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

}
