package com.splitwise.backend.settlement.domain;

import com.splitwise.backend.balance.service.BalanceServiceImpl;
import com.splitwise.backend.domain.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "settlements")
public class Settlement {
   
   @Id
   private UUID id;
   
   @ManyToOne
   @JoinColumn(name = "from_user", nullable = false)
   private User fromUser;
   
   @ManyToOne
   @JoinColumn(name = "to_user", nullable = false)
   private User toUser;
   
   @Column(nullable = false)
   private BigDecimal amount;
   
   private LocalDateTime createdAt;
   
   public Settlement (UUID uuid, User fromUser, User toUser, BigDecimal amount, LocalDateTime now) { }
}
