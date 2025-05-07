package com.cerenlacinel.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	public static final String SECRET_KEY = "M8VxBo6klQHtvBj2YPiD6PMZ1nAzBpttO93tLQIIRnA=";

	public String generateToken(UserDetails userDetails) {  // token  oluşturduk 
		return Jwts.builder()
		.setSubject(userDetails.getUsername())
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2)) //bitiş süresi 2 saat
		.signWith(getKey() , SignatureAlgorithm.HS256)
		.compact();	
		
	}
	
	
	public <T> T exportToken(String token, Function<Claims, T> claimsFunc) {
		Claims claims = getClaims(token);
		return claimsFunc.apply(claims); // almış olduğu claims metodunu claimsFunc metodunu kullanarak çöz
	}
	
	
	public Claims getClaims(String token) {  // claims alabilmek için 
		Claims claims = Jwts.parserBuilder()
		.setSigningKey(getKey())
		.build()
		.parseClaimsJws(token).getBody();
		
		return claims;
	}
	
	
	public String getUsernameByToken(String token) { //username çektik
		return exportToken(token, Claims::getSubject);
	}
	
	public boolean isTokenValid(String token) {  // bu token hala geçerli mi diye expirationa baktık
		Date expireDate = exportToken(token, Claims::getExpiration);
		return new Date().before(expireDate);
	}
	
	
	
	
	public Key getKey() {
		byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(bytes);
	}
}
