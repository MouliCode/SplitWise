package com.splitwise.backend.auth.dao.repository;

import com.splitwise.backend.auth.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByPhone(String phone);
	
	boolean existsByEmail(String email);
	
	boolean existsByPhone(String phone);
	
}
