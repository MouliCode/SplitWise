package com.splitwise.backend.group.controller;

import com.splitwise.backend.common.dto.response.ApiResponse;
import com.splitwise.backend.group.dto.request.AddGroupMemberRequest;
import com.splitwise.backend.group.dto.response.GroupMemberResponse;
import com.splitwise.backend.group.entity.Group;
import com.splitwise.backend.group.service.GroupMemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group/members")
public class GroupMemberController {
   
   private final GroupMemberService service;
   
   public GroupMemberController(GroupMemberService service){
	  this.service = service;
   }
   
   @PostMapping
   public ApiResponse<List<GroupMemberResponse>> addMember(
		   @Valid @RequestBody AddGroupMemberRequest request
		   ){
	  service.addMember (request.groupId (), request.userId ());
	  return ApiResponse.success (service.listMembers (request.groupId ()));
   }
   
   @DeleteMapping
   public ApiResponse<List<GroupMemberResponse>> removeMember(
		   @Valid @RequestBody AddGroupMemberRequest request
   ){
	  service.removeMember (request.groupId (), request.userId ());
	  return ApiResponse.success (service.listMembers (request.groupId ()));
   }
   
   @GetMapping
   public ApiResponse<List<GroupMemberResponse>> listMembers(
		   @Valid @RequestBody Group group
		   ){
	  return ApiResponse.success (service.listMembers (group.getId ()));
   }
   
   
   
}
