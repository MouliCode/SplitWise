package com.splitwise.backend.group.service;

import com.splitwise.backend.group.dto.request.CreateGroupRequestDTO;
import com.splitwise.backend.group.dto.response.GroupResponse;

import java.util.List;

public interface GroupService {
   
   GroupResponse createGroup(CreateGroupRequestDTO request);
   
   List<GroupResponse> myGroups();
}
