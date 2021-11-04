package com.gbsfo.tutor.springboottutor.repository;

import com.gbsfo.tutor.springboottutor.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByGroupName(String groupName);
}
