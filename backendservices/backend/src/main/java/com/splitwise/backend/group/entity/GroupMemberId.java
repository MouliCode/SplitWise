package com.splitwise.backend.group.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class GroupMemberId implements Serializable {
   
   private UUID groupId;
   private UUID userId;
   
   protected GroupMemberId(){}
   
   public GroupMemberId(UUID groupId, UUID userId){
	  this.groupId = groupId;
	  this.userId = userId;
   }
   
   public UUID getGroupId () {
	  return groupId;
   }
   
   public UUID getUserId () {
	  return userId;
   }
   
   @Override
   public boolean equals (Object o) {
	  if (o == null || getClass () != o.getClass ()) { return false; }
	  GroupMemberId that = (GroupMemberId) o;
	  return Objects.equals (groupId, that.groupId) && Objects.equals (userId, that.userId);
   }
   
   @Override
   public int hashCode () {
	  return groupId.hashCode () + userId.hashCode ();
   }
}
