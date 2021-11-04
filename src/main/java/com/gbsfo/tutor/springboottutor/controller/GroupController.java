package com.gbsfo.tutor.springboottutor.controller;

import com.gbsfo.tutor.springboottutor.dto.GroupDTO;
import com.gbsfo.tutor.springboottutor.model.Group;
import com.gbsfo.tutor.springboottutor.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/groups")
public class GroupController {
    
    private final GroupService groupService;
    
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    
    @PostMapping
    public GroupDTO addGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.addGroup(groupDTO);
    }
    
    @GetMapping("/{id}")
    public GroupDTO getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }
    
    @GetMapping
    public List<GroupDTO> getAllGroups() {
        return groupService.getAllGroups();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGroup(@PathVariable Long id) {
        groupService.removeGroup(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @GetMapping("/teacher/{id}")
    public ResponseEntity<Set<Group>> getAllTeachersFromGroup(@PathVariable Long id) {
        Set<Group> groups = groupService.getGroupsForTeacher(id);
        return ResponseEntity.ok().body(groups);
    }
}
