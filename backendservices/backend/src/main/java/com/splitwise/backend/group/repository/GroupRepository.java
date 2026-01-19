package com.splitwise.backend.group.repository;

import com.splitwise.backend.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
   
   List<Group> findByCreatedBy(UUID userId);
}
