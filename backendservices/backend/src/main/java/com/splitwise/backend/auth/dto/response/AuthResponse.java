package com.splitwise.backend.auth.dto.response;

import java.util.UUID;

public record AuthResponse(
		UUID userId,
		String name,
		String email,
		String phone,
		String token) { }
