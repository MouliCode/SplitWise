package com.splitwise.backend.auth.dto.response;

import java.util.UUID;

public class AuthResponse {
   
   private UUID   userId;
   private String name;
   private String phone;
   private String email;
   private String token;
   
   public AuthResponse (UUID userId, String name, String email, String phone, String token) {
	  this.userId = userId;
	  this.email  = email;
	  this.phone  = phone;
	  this.name   = name;
	  this.token  = token;
   }
   
   public UUID getUserId () {
	  return userId;
   }
   
   public String getName () {
	  return name;
   }
   
   public String getPhone () {
	  return phone;
   }
   
   public String getEmail () {
	  return email;
   }
   public String getToken(){
	  return token;
   }
}
