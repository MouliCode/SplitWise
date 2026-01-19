package com.splitwise.backend.group.controller;

import com.splitwise.backend.common.dto.response.ApiResponse;
import com.splitwise.backend.group.dto.request.CreateGroupRequestDTO;
import com.splitwise.backend.group.dto.response.GroupResponse;
import com.splitwise.backend.group.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
   
   private final GroupService service;
   
   public GroupController(GroupService service){
	  this.service = service;
   }
   
   @PostMapping
   public ApiResponse<GroupResponse> create(
		   @Valid @RequestBody CreateGroupRequestDTO request){
	  return  ApiResponse.success (service.createGroup (request));
   }
   
   
   @GetMapping("/myGroups")
   public ApiResponse<List<GroupResponse>> myGroups(){
	  return ApiResponse.success (service.myGroups ());
   }
}
