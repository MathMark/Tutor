package com.gbsfo.tutor.springboottutor.controller;

import com.gbsfo.tutor.springboottutor.dto.TeacherDTO;
import com.gbsfo.tutor.springboottutor.model.ResponseObject;
import com.gbsfo.tutor.springboottutor.projection.TeachersGroupView;
import com.gbsfo.tutor.springboottutor.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    
    @PostMapping
    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody TeacherDTO teacherDTO) {
        TeacherDTO teacherDTO1 = teacherService.addTeacher(teacherDTO);
        return ResponseEntity.ok().body(teacherDTO1);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok().body(teacherService.getTeacherById(id));
    }
    
    @GetMapping
    public List<TeacherDTO> getAllTeachers() {
       return teacherService.getAllTeachers();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteTeacherById(@PathVariable Long id) {
        teacherService.removeTeacher(id);
        ResponseObject responseObject = new ResponseObject(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok().body(responseObject);
    }
    
    @GetMapping("/group/{id}")
    public ResponseEntity<List<TeachersGroupView>> getAllTeachersOfGroup(@PathVariable Long id) {
        return null;
    }
}
