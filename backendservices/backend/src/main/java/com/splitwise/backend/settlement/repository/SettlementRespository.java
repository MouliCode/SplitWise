package com.splitwise.backend.settlement.repository;

import com.splitwise.backend.settlement.domain.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SettlementRespository extends JpaRepository<Settlement, UUID> {
}
