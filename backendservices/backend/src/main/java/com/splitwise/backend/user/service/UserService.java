package com.splitwise.backend.user.service;

import com.splitwise.backend.user.dto.UpdateUserRequest;
import com.splitwise.backend.user.dto.UserResponse;

public interface UserService {
	
	UserResponse getCurrentUser ();
	
	UserResponse updateProfile (UpdateUserRequest request);
}
