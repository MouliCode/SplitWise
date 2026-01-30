package com.splitwise.backend.settlement.domain;

import com.splitwise.backend.domain.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
   @Column(name = "id", nullable = false)
   private UUID id;   // ✅ manually assigned
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "from_user", nullable = false)
   private User fromUser;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "to_user", nullable = false)
   private User toUser;
   
   @Column(nullable = false)
   private BigDecimal amount;
   
   @Column(name = "created_at", nullable = false)
   private LocalDateTime createdAt;
   
   public Settlement (UUID uuid, User fromUser, User toUser, BigDecimal amount, LocalDateTime now) { }
}
