package com.splitwise.backend.common.security;


import java.util.UUID;

public final class UserContext {
	
	private static final ThreadLocal<UserContext> CURRENT_USER = new ThreadLocal<> ();
	
	private final UUID   id;
	private final String name;
	private final String phone;
	private final String email;
	
	private UserContext (UUID id, String name, String phone, String email) {
		this.id    = id;
		this.name  = name;
		this.phone = phone;
		this.email = email;
	}
	
	public static void set (UUID id, String name, String phone, String email) {
		CURRENT_USER.set (new UserContext (id, name, phone, email));
	}
	
	public static UserContext get () {
		return CURRENT_USER.get ();
	}
	
	public UUID getId () {
		return id;
	}
	
	public static void clear () {
		CURRENT_USER.remove ();
	}
}
