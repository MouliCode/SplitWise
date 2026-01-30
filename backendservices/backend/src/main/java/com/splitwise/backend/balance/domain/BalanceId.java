package com.splitwise.backend.balance.domain;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record BalanceId(
		UUID fromUser,
		UUID toUser
) {}
