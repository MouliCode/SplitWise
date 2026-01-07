package com.splitwise.backend.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
	
	@NotBlank(message = "Name cannot be Empty")
	private String name;
	
	private String phone;
	
	private String email;
	
	private String password;
	
	public String getName () {
		return name;
	}
	
	public String getPhone () {
		return phone;
	}
	
	public String getEmail () {
		return email;
	}
	
	public String getPassword () {
		return password;
	}
}
