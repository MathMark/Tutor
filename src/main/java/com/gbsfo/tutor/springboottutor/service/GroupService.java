package com.gbsfo.tutor.springboottutor.service;

import com.gbsfo.tutor.springboottutor.dto.GroupDTO;
import com.gbsfo.tutor.springboottutor.mapper.Mapper;
import com.gbsfo.tutor.springboottutor.model.Group;
import com.gbsfo.tutor.springboottutor.model.Teacher;
import com.gbsfo.tutor.springboottutor.repository.GroupRepository;
import com.gbsfo.tutor.springboottutor.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService implements Mapper<Group, GroupDTO> {
    
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    
    @Autowired
    public GroupService(GroupRepository groupRepository, TeacherRepository teacherRepository) {
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
    }
    
    @Transactional
	public GroupDTO addGroup(GroupDTO groupDTO) {
		Group group = mapToEntity(groupDTO);
		Group savedGroup = groupRepository.save(group);
		return mapToDto(savedGroup);
	}


	public List<GroupDTO> getAllGroups() {
		List<GroupDTO> groupDtos = new ArrayList<>();
		List<Group> groups = groupRepository.findAll();
		groups.forEach(group -> {
			GroupDTO courseDto = mapToDto(group);
			groupDtos.add(courseDto);
		});
		return groupDtos;
	}
	
	@Transactional
	public GroupDTO updateGroup(Long id, GroupDTO groupDTO) {
		Optional<Group> optionalGroup = groupRepository.findById(id);
		if (optionalGroup.isPresent()) {
		    Group group = optionalGroup.get();
		    group.getTeachersList().clear();
		    group = mapToEntity(groupDTO);
		    Group updated = groupRepository.save(group);
		    return mapToDto(updated);
        }
		return null;
	}

	@Transactional
	public String removeGroup(Long id) {
		Optional<Group> group = groupRepository.findById(id);
		if(group.isPresent()) {
			group.get().removeStudents();
			groupRepository.deleteById(group.get().getId());
			return "Course with id: " + id + " deleted successfully!";
		}
		return null;
	}
	
    public GroupDTO getGroupById(Long id) {
		Group group = groupRepository.getById(id);
		return mapToDto(group);
    }
    
    @Override
    public GroupDTO mapToDto(Group group) {
        GroupDTO responseDto = new GroupDTO();
		responseDto.setGroupName(group.getGroupName());
		responseDto.setId(group.getId());
		responseDto.setTeachers(group.getTeachersList().stream().map(Teacher::getLastName).collect(Collectors.toSet()));
		return responseDto;
    }

    @Override
    public Group mapToEntity(GroupDTO groupDTO) {
        Group group = new Group();
		group.setGroupName(groupDTO.getGroupName());
		group.setStartDate(groupDTO.getStartDate());
		group.setFinishDate(groupDTO.getFinishDate());
		if (group.getTeachersList() == null) {
			group.setTeachersList(new HashSet<>());
		}
		groupDTO.getTeachers().forEach(teacherLastName -> {
			Teacher teacher = teacherRepository.findByLastName(teacherLastName);
			if (teacher == null) {
				teacher = new Teacher();
				teacher.setGroups(new HashSet<>());
			}
			teacher.setLastName(teacherLastName);
			teacher.addGroup(group);
		});
		return group;
    }
}
