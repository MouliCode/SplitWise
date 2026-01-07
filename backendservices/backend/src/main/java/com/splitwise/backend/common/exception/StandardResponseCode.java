package com.splitwise.backend.common.exception;

public enum StandardResponseCode {
	
	SUCCESS ("success"),
	VALIDATION_ERROR ("invalid request data"),
	INTERNAL_ERROR ("something went wrong"),
	FAILURE ("failure"),
	EMAIL_OR_PHONE_ERROR ("email or phone number is required"),
	EMAIL_EXISTS ("email already exists"),
	PHONE_NUMBER_EXISTS ("phone number already exists"),
	PASSWORD_ERROR("invalid password"),
	UNAUTHORIZED("user not authenticated"),
	USER_NOT_FOUND("user not found");
	
	
	private final String message;
	
	StandardResponseCode () {
		this.message = name ().toLowerCase ();
	}
	
	StandardResponseCode (String message) {
		this.message = message;
	}
	
	@Override
	public String toString () {
		return message;
	}
	
	public String getMessage () {
		return message;
	}
}
