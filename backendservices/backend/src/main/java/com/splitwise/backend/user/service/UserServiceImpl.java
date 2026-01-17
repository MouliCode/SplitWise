package com.splitwise.backend.user.service;

import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import com.splitwise.backend.common.security.SecurityContextUtil;
import com.splitwise.backend.domain.entity.User;
import com.splitwise.backend.domain.repository.UserRepository;
import com.splitwise.backend.user.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
   
   private final UserRepository userRepository;
   
   public UserServiceImpl (UserRepository userRepository) {
	  this.userRepository = userRepository;
   }
   
   @Override
   public UserResponse getCurrentUser () {
	  
	  UUID userId = SecurityContextUtil.getCurrentUserId ();
	  
	  User user = userRepository.findById (userId)
						  .orElseThrow (() -> new BusinessException (StandardResponseCode.USER_NOT_FOUND,
								  StandardResponseCode.USER_NOT_FOUND.getMessage ()));
	  
	  return new UserResponse (
			  user.getId (),
			  user.getName (),
			  user.getEmail (),
			  user.getPhone ()
	  );
   }


//   @Override
//   public UserResponse updateProfile (UpdateUserRequest request) {
//
//	  User user = new User ();
//
//	  user.setName (request.getName ());
//	  user.setPhone (request.getPhone ());
//	  user.setEmail (request.getEmail ());
//	  user.setPasswordHash (request.getPassword ());
//
//	  User savedUser = repo.save (user);
//
//	  return mapToResponse (savedUser);
//   }
//
//   private UserResponse mapToResponse (User user) {
//	  return new UserResponse (
//			  user.getId (),
//			  user.getName (),
//			  user.getEmail (),
//			  user.getPhone ()
//	  );
//   }
//

}
