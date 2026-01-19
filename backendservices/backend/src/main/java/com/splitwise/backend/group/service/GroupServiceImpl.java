package com.splitwise.backend.group.service;

import com.splitwise.backend.common.security.SecurityContextUtil;
import com.splitwise.backend.group.dto.request.CreateGroupRequestDTO;
import com.splitwise.backend.group.dto.response.GroupResponse;
import com.splitwise.backend.group.entity.Group;
import com.splitwise.backend.group.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupServiceImpl implements  GroupService{
   
   private final GroupRepository groupRepository;
   
   public GroupServiceImpl(GroupRepository groupRepository){
	  this.groupRepository = groupRepository;
   }
   
   @Override
   public GroupResponse createGroup (CreateGroupRequestDTO request) {
	  
	  UUID currentUserId = SecurityContextUtil.getCurrentUserId ();
	  
	  Group group = new Group (
			  UUID.randomUUID (),
			  request.name(),
			  request.description (),
			  currentUserId
	  );
	  
	  Group saved = groupRepository.save(group);
	  
	  return mapToResponse(saved);
   }
   
   @Override
   public List<GroupResponse> myGroups () {
	 
	  UUID currentUserId = SecurityContextUtil.getCurrentUserId ();
	  
	  return groupRepository.findByCreatedBy (currentUserId)
					 .stream()
					 .map(this:: mapToResponse)
					 .toList ();
	  
   }
   
   private GroupResponse mapToResponse(Group group){
	  
	  return new GroupResponse (
			  group.getId(),
			  group.getName(),
			  group.getDescription (),
			  group.getCreatedBy (),
			  group.getCreatedAt ()
	  );
   }
}
