package com.splitwise.backend.group.entity;

import com.splitwise.backend.domain.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_members")
public class GroupMember {
   
   @EmbeddedId
   private GroupMemberId id;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @MapsId("groupId")
   @JoinColumn(name = "group_id", nullable = false)
   private Group group;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @MapsId("userId")
   @JoinColumn(name = "user_id", nullable = false)
   private User user;
   
   @Column(nullable = false)
   private String role;
   
   @Column(name = "joined_at")
   private LocalDateTime joinedAt;
   
   protected GroupMember () { }
   
   public GroupMember (Group group, User user, String role) {
	  this.id    = new GroupMemberId (group.getId (), user.getId ());
	  this.group = group;
	  this.user  = user;
	  this.role  = role;
      this.joinedAt = LocalDateTime.now();
   }
   
   public GroupMemberId getId () {
      return id;
   }
   
   public Group getGroup () {
      return group;
   }
   
   public User getUser () {
      return user;
   }
   
   public String getRole () {
      return role;
   }
   
   public LocalDateTime getJoinedAt () {
      return joinedAt;
   }
}
