package com.splitwise.backend.group.service;

import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import com.splitwise.backend.domain.entity.User;
import com.splitwise.backend.domain.repository.UserRepository;
import com.splitwise.backend.group.dto.response.GroupMemberResponse;
import com.splitwise.backend.group.entity.Group;
import com.splitwise.backend.group.entity.GroupMember;
import com.splitwise.backend.group.repository.GroupMemberRepository;
import com.splitwise.backend.group.repository.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GroupMemberServiceImpl implements GroupMemberService {
   
   private final UserRepository        userRepository;
   private final GroupRepository       groupRepository;
   private final GroupMemberRepository groupMemberRepository;
   
   public GroupMemberServiceImpl (UserRepository userRepository,
								  GroupRepository groupRepository,
								  GroupMemberRepository groupMemberRepository) {
	  this.userRepository        = userRepository;
	  this.groupRepository       = groupRepository;
	  this.groupMemberRepository = groupMemberRepository;
   }
   
   @Override
   public void addMember (UUID groupId, UUID userId) {
	  
	  if (groupMemberRepository.existsByGroup_IdAndUser_Id (groupId, userId)) {
		 throw new BusinessException (StandardResponseCode.USER_ALREADY_IN_GROUP,
				 StandardResponseCode.USER_ALREADY_IN_GROUP.getMessage ());
	  }
	  
	  Group group = groupRepository.findById (groupId)
							.orElseThrow (() ->
												  new BusinessException (StandardResponseCode.GROUP_NOT_FOUND,
														  StandardResponseCode.GROUP_NOT_FOUND.getMessage ()));
	  
	  User user = userRepository.findById (userId)
						  .orElseThrow (() ->
												new BusinessException (StandardResponseCode.USER_NOT_FOUND,
														StandardResponseCode.USER_NOT_FOUND.getMessage ()));
	  
	  GroupMember member = new GroupMember (group, user, "MEMBER");
	  groupMemberRepository.save(member);
	  
	  
   }
   
   @Override
   public void removeMember (UUID groupId, UUID userId) {
	  
	  if(!groupMemberRepository.existsByGroup_IdAndUser_Id (groupId, userId)){
		 throw new BusinessException (StandardResponseCode.USER_NOT_FOUND, StandardResponseCode.USER_NOT_FOUND.getMessage ());
	  }
	  groupMemberRepository.deleteByGroup_IdAndUser_Id (groupId, userId);
	  
   }
   
   @Override
   public List<GroupMemberResponse> listMembers (UUID groupId) {
	  return groupMemberRepository.findByGroup_Id (groupId)
					 .stream ()
					 .map (m -> new GroupMemberResponse (
							 m.getUser ().getId (),
							 m.getUser ().getName (),
							 m.getRole ()
					 ))
					 .toList ();
   }
}
