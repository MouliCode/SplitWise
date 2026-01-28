package com.splitwise.backend.expense.domain;

import com.splitwise.backend.domain.entity.User;
import com.splitwise.backend.expense.SplitType;
import com.splitwise.backend.group.entity.Group;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "expenses")
public class Expense {
   
   @Id
   private UUID id;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "group_id", nullable = false)
   private Group group;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "paid_by", nullable = false)
   private User paidBy;
   
   @Column(nullable = false, precision = 12, scale = 2)
   private BigDecimal amount;
   
   private String description;
   
   @Enumerated(EnumType.STRING)
   @Column(name = "split_type", nullable = false)
   private SplitType splitType;
   
   @OneToMany(
		   mappedBy = "expense",
					 cascade = CascadeType.ALL,
					 orphanRemoval = true
   )
   
   private List<ExpenseSplit> splits = new ArrayList<> ();
   
   @Column(name = "created_at")
   private LocalDateTime       createdAt;
   
   @PrePersist
   void prePersist () {
	  this.id        = UUID.randomUUID ();
	  this.createdAt = LocalDateTime.now ();
   }
   
   public UUID getId () {
	  return id;
   }
   
   public Group getGroup () {
	  return group;
   }
   
   public User getPaidBy () {
	  return paidBy;
   }
   
   public BigDecimal getAmount () {
	  return amount;
   }
   
   public String getDescription () {
	  return description;
   }
   
   public LocalDateTime getCreatedAt () {
	  return createdAt;
   }
   
   public void setId (UUID id) {
	  this.id = id;
   }
   
   public void setGroup (Group group) {
	  this.group = group;
   }
   
   public void setPaidBy (User paidBy) {
	  this.paidBy = paidBy;
   }
   
   public void setAmount (BigDecimal amount) {
	  this.amount = amount;
   }
   
   public void setDescription (String description) {
	  this.description = description;
   }
   
   public void setCreatedAt (LocalDateTime createdAt) {
	  this.createdAt = createdAt;
   }
   
   public void setSplitType (SplitType splitType) {
	  this.splitType = splitType;
   }
   
   public void setSplits (List<ExpenseSplit> splits) {
	  this.splits = splits;
   }
   
   public SplitType getSplitType () {
	  return splitType;
   }
   
   public List<ExpenseSplit> getSplits () {
	  return splits;
   }
}
