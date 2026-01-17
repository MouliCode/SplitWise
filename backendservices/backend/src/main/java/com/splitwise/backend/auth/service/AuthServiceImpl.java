package com.splitwise.backend.auth.service;

import com.splitwise.backend.auth.dto.request.LoginRequest;
import com.splitwise.backend.auth.dto.request.SignupRequest;
import com.splitwise.backend.auth.dto.response.AuthResponse;
import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import com.splitwise.backend.common.security.jwt.JwtTokenProvider;
import com.splitwise.backend.domain.entity.User;
import com.splitwise.backend.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
   
   private final UserRepository   userRepository;
   private final PasswordEncoder  passwordEncoder;
   private final JwtTokenProvider jwtTokenProvider;
   
   public AuthServiceImpl (
		   UserRepository userRepository,
		   PasswordEncoder passwordEncoder,
		   JwtTokenProvider jwtTokenProvider
   ) {
	  this.userRepository   = userRepository;
	  this.passwordEncoder  = passwordEncoder;
	  this.jwtTokenProvider = jwtTokenProvider;
   }
   
   @Override
   public AuthResponse signup (SignupRequest request) {
	  
	  if (request.email () == null && request.phone () == null) {
		 throw new BusinessException (StandardResponseCode.EMAIL_OR_PHONE_ERROR,
				 StandardResponseCode.EMAIL_OR_PHONE_ERROR.getMessage ());
	  }
	  
	  if (request.email() != null && userRepository.existsByEmail (request.email ())) {
		 throw new BusinessException (StandardResponseCode.EMAIL_EXISTS,
				 StandardResponseCode.EMAIL_EXISTS.getMessage ());
	  }
	  
	  if (request.phone () != null && userRepository.existsByPhone (request.phone ())) {
		 throw new BusinessException (StandardResponseCode.PHONE_NUMBER_EXISTS,
				 StandardResponseCode.PHONE_NUMBER_EXISTS.getMessage ());
	  }
	  
	  User user = new User ();
	  user.setId (UUID.randomUUID ());
	  user.setName (request.name ());
	  user.setEmail (request.email ());
	  user.setPhone (request.phone ());
	  user.setPasswordHash (passwordEncoder.encode (request.password ()));
	  
	  User savedUser = userRepository.save (user);
	  
	  String token = jwtTokenProvider.generateToken (
			  savedUser.getId (),
			  savedUser.getName ()
	  );
	  
	  return new AuthResponse (
			  savedUser.getId (),
			  savedUser.getName (),
			  savedUser.getEmail (),
			  savedUser.getPhone (),
			  token
	  );
   }
   
   @Override
   public AuthResponse login (LoginRequest request) {
	  
	  if (request.email () == null && request.phone () == null) {
		 throw new BusinessException (StandardResponseCode.EMAIL_OR_PHONE_ERROR,
				 StandardResponseCode.EMAIL_OR_PHONE_ERROR.getMessage ());
	  }
	  
	  User user = request.email () != null ? userRepository.findByEmail (request.email ())
														.orElseThrow (() -> new BusinessException (StandardResponseCode.EMAIL_OR_PHONE_ERROR,
																StandardResponseCode.EMAIL_OR_PHONE_ERROR.getMessage ()))
											  : userRepository.findByPhone (request.phone ())
														.orElseThrow (() -> new BusinessException (StandardResponseCode.EMAIL_OR_PHONE_ERROR,
																StandardResponseCode.EMAIL_OR_PHONE_ERROR.getMessage ()));
	  
	  if(!passwordEncoder.matches (request.password (), user.getPasswordHash ())){
		 throw new BusinessException (StandardResponseCode.PASSWORD_ERROR, StandardResponseCode.PASSWORD_ERROR.getMessage ());
	  }
	  
	  String token = jwtTokenProvider.generateToken (
			  user.getId(),
			  user.getName()
	  );
		 return new AuthResponse (
				 user.getId (),
				 user.getName (),
				 user.getEmail (),
				 user.getPhone (),
				 token);
   }
   
   private User resolveUser(LoginRequest request){
	  
	  Optional<User> userOpt;
	  
	  if(request.email () != null && !request.email ().isBlank ()){
		 userOpt = userRepository.findByEmail (request.email ());
	  }else{
		 userOpt = userRepository.findByPhone (request.phone ());
	  }
	  
	  return userOpt.orElseThrow (() ->
										 new BusinessException (StandardResponseCode.EMAIL_OR_PHONE_ERROR,
												 StandardResponseCode.EMAIL_OR_PHONE_ERROR.getMessage ()));
   }
}
