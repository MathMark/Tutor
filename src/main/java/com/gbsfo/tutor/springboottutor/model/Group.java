package com.gbsfo.tutor.springboottutor.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "group_name", nullable = false, unique = true)
    private String groupName;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "finish_date", nullable = false)
    private LocalDate finishDate;
    
    @ManyToMany(mappedBy = "groups")
    private Set<Teacher> teachersList;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<Student> studentsList;
    
    public void removeTeacher(Teacher teacher) {
		this.getTeachersList().remove(teacher);
		teacher.getGroups().remove(this);
	}

	public void removeStudents() {
		for (Teacher teacher : new HashSet<>(teachersList)) {
			removeTeacher(teacher);
		}
	}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Set<Teacher> getTeachersList() {
        return teachersList;
    }

    public void setTeachersList(Set<Teacher> teachersList) {
        this.teachersList = teachersList;
    }

    public Set<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(Set<Student> studentsList) {
        this.studentsList = studentsList;
    }
}
