package com.splitwise.backend.balance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@Embeddable
public class BalanceId implements Serializable {
   
   @Column(name = "from_user")
   private UUID fromUser;
   
   @Column(name = "to_user")
   private UUID toUser;
   
   public BalanceId (UUID fromUser, UUID toUser) {
	  this.fromUser = fromUser;
	  this.toUser   = toUser;
   }
   
   public BalanceId(){}
   
   public UUID getFromUser () {
	  return fromUser;
   }
   
   public UUID getToUser () {
	  return toUser;
   }
   
   @Override
   public boolean equals (Object o) {
	  if (!(o instanceof BalanceId balanceId)) { return false; }
	  return Objects.equals (fromUser, balanceId.fromUser) && Objects.equals (toUser, balanceId.toUser);
   }
   
   @Override
   public int hashCode () {
	  return Objects.hash (fromUser, toUser);
   }
}
