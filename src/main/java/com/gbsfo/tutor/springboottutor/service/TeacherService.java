package com.gbsfo.tutor.springboottutor.service;

import com.gbsfo.tutor.springboottutor.dto.TeacherDTO;
import com.gbsfo.tutor.springboottutor.mapper.Mapper;
import com.gbsfo.tutor.springboottutor.model.Gender;
import com.gbsfo.tutor.springboottutor.model.Group;
import com.gbsfo.tutor.springboottutor.model.Teacher;
import com.gbsfo.tutor.springboottutor.model.TeacherStatus;
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
public class TeacherService implements Mapper<Teacher, TeacherDTO> {

    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    
    @Autowired
    public TeacherService(TeacherRepository teacherRepository, GroupRepository groupRepository) {
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
    }
    
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.getById(id);
        return mapToDto(teacher);
    }
    
    @Transactional
    public TeacherDTO addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = mapToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return mapToDto(savedTeacher);
    }
    
	public List<TeacherDTO> getAllTeachers() {
		List<TeacherDTO> teacherDTOS = new ArrayList<>();
		List<Teacher> teachers = teacherRepository.findAll();
		teachers.forEach(teacher -> {
			TeacherDTO teacherDTO = mapToDto(teacher);
			teacherDTOS.add(teacherDTO);
		});
		return teacherDTOS;
	}

	@Transactional
	public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
		Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
		if (optionalTeacher.isPresent()) {
		    Teacher teacher = optionalTeacher.get();
		    teacher.getGroups().clear();
		    teacher = mapToEntity(teacherDTO);
		    Teacher updatedTeacher = teacherRepository.save(teacher);
		    return mapToDto(updatedTeacher);
        }
		return null;
	}
	
	public String removeTeacher(Long id) {
		Optional<Teacher> teacher = teacherRepository.findById(id);
		if(teacher.isPresent()) {
			teacher.get().removeGroups();
			teacherRepository.deleteById(teacher.get().getId());
			return "Student with id: " + id + " deleted successfully!";
		}
		return null;
	}
	
    @Override
    public TeacherDTO mapToDto(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setPosition(teacher.getPosition());
        teacherDTO.setBirthDate(teacher.getBirthDate());
        teacherDTO.setTeacherStatus(teacher.getTeacherStatus().toStringLine());
        teacherDTO.setSex(teacher.getTeacherStatus().toStringLine());
        teacherDTO.setId(teacher.getId());
        teacherDTO.setGroups(teacher.getGroups().stream().map(Group::getGroupName).collect(Collectors.toSet()));
        return teacherDTO;
    }

    @Override
    public Teacher mapToEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setPosition(teacherDTO.getPosition());
        teacher.setSex(Gender.fromString(teacherDTO.getSex()));
        teacher.setBirthDate(teacherDTO.getBirthDate());
        teacher.setTeacherStatus(TeacherStatus.fromString(teacherDTO.getTeacherStatus()));
        if (teacher.getGroups() == null) {
            teacher.setGroups(new HashSet<>());
        }
        teacherDTO.getGroups().forEach(groupName -> {
            Group group = groupRepository.findByGroupName(groupName);
            if (null == group) {
                group = new Group();
                group.setTeachersList(new HashSet<>());
            }
            group.setGroupName(groupName);
            teacher.addGroup(group);
        });
        return teacher;
    }
}
