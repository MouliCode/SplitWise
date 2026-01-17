package com.splitwise.backend.user.controller;

import com.splitwise.backend.common.dto.response.ApiResponse;
import com.splitwise.backend.user.dto.UserResponse;
import com.splitwise.backend.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
   
   private final UserService service;
   
   public UserController (UserService service) { this.service = service; }
   
   @GetMapping("/me")
   public ApiResponse<UserResponse> me () {
	  return ApiResponse.success (service.getCurrentUser ());
   }

//	@PutMapping("/update")
//	public ApiResponse<UserResponse> updateProfile(
//			@Valid @RequestBody UpdateUserRequest request
//			){
//		return  ApiResponse.success (service.updateProfile (request));
//	}
}
