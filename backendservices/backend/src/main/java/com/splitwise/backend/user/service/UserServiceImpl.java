package com.splitwise.backend.user.service;

import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import com.splitwise.backend.domain.entity.User;
import com.splitwise.backend.domain.repository.UserRepository;
import com.splitwise.backend.user.dto.UpdateUserRequest;
import com.splitwise.backend.user.dto.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository repo;
	
	public UserServiceImpl (UserRepository repo) { this.repo = repo; }
	
	@Override
	public UserResponse getCurrentUser () {
		
		Authentication auth = SecurityContextHolder.getContext ().getAuthentication ();
		
		if (auth == null || !auth.isAuthenticated ()
				    || "anonymousUser".equals (auth.getPrincipal ())) {
			throw new BusinessException (StandardResponseCode.UNAUTHORIZED,
					StandardResponseCode.UNAUTHORIZED.getMessage ());
		}
		
		String identifier = auth.getName ();


//		UserContext ctx = UserContext.get ();

//		if (ctx == null) {
//			throw new BusinessException (StandardResponseCode.UNAUTHORIZED,
//					StandardResponseCode.UNAUTHORIZED.getMessage ());
//		}
//
		User user =
				repo.findByEmail (identifier).orElseThrow (() -> new BusinessException (StandardResponseCode
				.USER_NOT_FOUND, StandardResponseCode.USER_NOT_FOUND.getMessage ()));

		return mapToResponse (user);
	}
	
	@Override
	public UserResponse updateProfile (UpdateUserRequest request) {
		
		User user = new User ();
		
		user.setName (request.getName ());
		user.setPhone (request.getPhone ());
		user.setEmail (request.getEmail ());
		user.setPasswordHash (request.getPassword ());
		
		User savedUser = repo.save (user);
		
		return mapToResponse (savedUser);
	}
	
	private UserResponse mapToResponse (User user) {
		return new UserResponse (
				user.getId (),
				user.getName (),
				user.getEmail (),
				user.getPhone ()
		);
	}
	
	
}
