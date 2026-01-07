package com.splitwise.backend.user.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserResponse {
	
	private UUID   id;
	private String name;
	private String email;
	private String phone;
	
	public UserResponse (UUID id, String name, String email, String phone) { }
}
