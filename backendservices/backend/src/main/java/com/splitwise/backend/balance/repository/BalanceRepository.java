package com.splitwise.backend.balance.repository;

import com.splitwise.backend.balance.domain.Balance;
import com.splitwise.backend.balance.domain.BalanceId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BalanceRepository extends JpaRepository<Balance, BalanceId> {
   
   List<Balance> findById_FromUser(UUID fromUserId); //user owes other
   
   Optional<Balance> findByIdFromUserAndIdToUser(UUID fromUserId, UUID toUserId); // others owe user
}
