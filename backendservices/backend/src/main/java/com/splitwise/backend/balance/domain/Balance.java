package com.splitwise.backend.balance.domain;

import com.splitwise.backend.domain.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "balances")
public class Balance {
   
   @EmbeddedId
   private BalanceId id;
   
   @Column(nullable = false, precision = 18, scale = 2)
   private BigDecimal amount;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @MapsId("fromUser")
   @JoinColumn(name = "from_User", nullable = false)
   private User fromUser;
   
   
   @ManyToOne(fetch = FetchType.LAZY)
   @MapsId("toUser")
   @JoinColumn(name= "to_User", nullable = false)
   private User toUser;
   
   protected Balance(){}
   
   public Balance(User fromUser, User toUser, BigDecimal amount){
	  this.id = new BalanceId (fromUser.getId(), toUser.getId ());
	  this.fromUser = fromUser;
	  this.toUser = toUser;
	  this.amount = amount;
   }
   
   public Balance(BalanceId id, BigDecimal amount){
	  this.id = id;
	  this.amount = amount;
   }
   
   public void addAmount(BigDecimal delta){
	  this.amount = this.amount.add(delta);
   }
   
   public BigDecimal getAmount(){
	  return amount;
   }
   
   public BalanceId getId () {
	  return id;
   }
   
   public User getFromUser () {
	  return fromUser;
   }
   
   public User getToUser () {
	  return toUser;
   }
   
   public void setId (BalanceId id) {
	  this.id = id;
   }
   
   public void setAmount (BigDecimal amount) {
	  this.amount = amount;
   }
   
   public void setFromUser (User fromUser) {
	  this.fromUser = fromUser;
   }
   
   public void setToUser (User toUser) {
	  this.toUser = toUser;
   }
}
