package com.splitwise.backend.auth.service;

import com.splitwise.backend.auth.dao.entity.User;
import com.splitwise.backend.auth.dao.repository.UserRepository;
import com.splitwise.backend.auth.dto.request.LoginRequest;
import com.splitwise.backend.auth.dto.request.SignupRequest;
import com.splitwise.backend.auth.dto.response.AuthResponse;
import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
	
	private final UserRepository        repo;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ();
	
	public AuthServiceImpl (UserRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public AuthResponse signup (SignupRequest request) {
		
		if (request.getEmail () == null && request.getPhone () == null) {
			throw new BusinessException (StandardResponseCode.EMAIL_OR_PHONE_ERROR,
					StandardResponseCode.EMAIL_OR_PHONE_ERROR.getMessage ());
		}
		
		if (request.getEmail () != null && repo.existsByEmail (request.getEmail ())) {
			throw new BusinessException (StandardResponseCode.EMAIL_EXISTS,
					StandardResponseCode.EMAIL_EXISTS.getMessage ());
		}
		
		if (request.getPhone () != null && repo.existsByPhone (request.getPhone ())) {
			throw new BusinessException (StandardResponseCode.PHONE_NUMBER_EXISTS,
					StandardResponseCode.PHONE_NUMBER_EXISTS.getMessage ());
		}
		
		User user = new User ();
		user.setEmail (request.getEmail ());
		user.setName (request.getName ());
		user.setPhone (request.getPhone ());
		user.setPasswordHash (passwordEncoder.encode (request.getPassword ()));
		
		repo.save (user);
		
		return new AuthResponse (
				user.getId (),
				user.getName (),
				user.getEmail (),
				user.getPhone ()
		);
	}
	
	@Override
	public AuthResponse login (LoginRequest request) {
		
		if (request.getEmail () == null && request.getPhone () == null) {
			throw new BusinessException (StandardResponseCode.EMAIL_OR_PHONE_ERROR,
					StandardResponseCode.EMAIL_OR_PHONE_ERROR.getMessage ());
		}
		
		User user = request.getEmail () != null || request.getPhone () != null
		            ? repo.findByEmail (request.getEmail ())
				              .orElseThrow (() -> new BusinessException (StandardResponseCode.EMAIL_OR_PHONE_ERROR,
						              StandardResponseCode.EMAIL_OR_PHONE_ERROR.getMessage ()))
		            : repo.findByPhone (request.getPhone ())
				              .orElseThrow (() -> new BusinessException (StandardResponseCode.EMAIL_OR_PHONE_ERROR,
						              StandardResponseCode.EMAIL_OR_PHONE_ERROR.getMessage ()));
		
		if (!passwordEncoder.matches (request.getPassword (), user.getPasswordHash ())) {
			throw new BusinessException (StandardResponseCode.PASSWORD_ERROR,
					StandardResponseCode.PASSWORD_ERROR.getMessage ());
		}
		
		return new AuthResponse (
				user.getId (),
				user.getName (),
				user.getEmail (),
				user.getPhone ()
		);
	}
}
