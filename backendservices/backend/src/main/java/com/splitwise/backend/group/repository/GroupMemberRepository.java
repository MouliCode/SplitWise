package com.splitwise.backend.group.repository;

import com.splitwise.backend.group.entity.GroupMember;
import com.splitwise.backend.group.entity.GroupMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberId> {
   
   List<GroupMember> findByGroup_Id(UUID groupId);
   
   boolean existsByGroup_IdAndUser_Id (UUID groupId, UUID userId);
   
   void deleteByGroup_IdAndUser_Id(UUID groupId, UUID userId);
   
}
