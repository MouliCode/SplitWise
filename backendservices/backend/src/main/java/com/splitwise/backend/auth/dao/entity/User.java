package com.splitwise.backend.auth.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(nullable = false)
	private UUID id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(unique = true, length = 150)
	private String email;
	
	@Column(unique = true, length = 20)
	private String phone;
	
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@PrePersist
	void onCreate () {
		this.id        = UUID.randomUUID ();
		this.createdAt = LocalDateTime.now ();
	}
	
	public UUID getId () {
		return id;
	}
	
	public void setId ( UUID id ) {
		this.id = id;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName ( String name ) {
		this.name = name;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail ( String email ) {
		this.email = email;
	}
	
	public String getPhone () {
		return phone;
	}
	
	public void setPhone ( String phone ) {
		this.phone = phone;
	}
	
	public String getPasswordHash () {
		return passwordHash;
	}
	
	public void setPasswordHash ( String passwordHash ) {
		this.passwordHash = passwordHash;
	}
	
	public LocalDateTime getCreatedAt () {
		return createdAt;
	}
	
	public void setCreatedAt ( LocalDateTime createdAt ) {
		this.createdAt = createdAt;
	}
}
