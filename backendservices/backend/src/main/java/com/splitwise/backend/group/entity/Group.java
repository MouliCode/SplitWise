package com.splitwise.backend.group.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "groups")
public class Group {
   
   @Id
   @Column(nullable = false, updatable = false)
   private UUID id;
   
   @Column(nullable = false, length = 100)
   private String name;
   
   @Column(length = 255)
   private String description;
   
   @Column(name = "created_by", nullable = false)
   private UUID createdBy;
   
   @Column(name = "created_at", nullable = false)
   private LocalDateTime createdAt;
   
   protected Group () { }
   
   public Group (UUID id, String name, String description, UUID createdBy) {
	  this.id          = id;
	  this.name        = name;
	  this.description = description;
	  this.createdBy   = createdBy;
	  this.createdAt   = LocalDateTime.now ();
   }
   
   public String getName () {
	  return name;
   }
   
   public UUID getId () {
	  return id;
   }
   
   public String getDescription () {
	  return description;
   }
   
   public UUID getCreatedBy () {
	  return createdBy;
   }
   
   public LocalDateTime getCreatedAt () {
	  return createdAt;
   }
}
