package com.splitwise.backend.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {
   
   private final SecretKey secretKey;
   private final long expirationMs;
   
   public JwtTokenProvider(
		   @Value("${jwt.secret}") String secret,
		   @Value("${jwt.expiration}") long expirationMs
   ) {
	  this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	  this.expirationMs = expirationMs;
   }
   
   public String generateToken(UUID userId, String name) {
	  return Jwts.builder()
					 .subject(userId.toString())
					 .claim("name", name)
					 .issuedAt(new Date())
					 .expiration(new Date(System.currentTimeMillis() + expirationMs))
					 .signWith(secretKey)
					 .compact();
   }
   
   public UUID extractUserId(String token) {
	  return UUID.fromString(getClaims(token).getSubject());
   }
   
   public boolean validateToken(String token) {
	  try {
		 getClaims(token);
		 return true;
	  } catch (Exception e) {
		 return false;
	  }
   }
   
   private Claims getClaims(String token) {
	  return Jwts.parser()
					 .verifyWith(secretKey)
					 .build()
					 .parseSignedClaims(token)
					 .getPayload();
   }
}
