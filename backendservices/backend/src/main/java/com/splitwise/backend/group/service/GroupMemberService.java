package com.splitwise.backend.group.service;

import com.splitwise.backend.group.dto.response.GroupMemberResponse;

import java.util.List;
import java.util.UUID;

public interface GroupMemberService {
   
   void addMember(UUID groupId, UUID userId);
   
   void removeMember(UUID groupId, UUID userId);
   
   List<GroupMemberResponse> listMembers(UUID groupId);
}
