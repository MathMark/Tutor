package com.gbsfo.tutor.springboottutor.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "teacher_group", 
        joinColumns = { @JoinColumn(name = "teacher_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "group_id") }
    )
    private Set<Group> groups = new HashSet<>();
    
    @Column(name = "position", nullable = false)
    private String position;
    
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TeacherStatus teacherStatus;
    
    public void addGroup(Group group) {
        this.groups.add(group);
        group.getTeachersList().add(this);
    }
    public void removeGroup(Group group) {
        this.getGroups().remove(group);
        group.getTeachersList().remove(this);
    }
    public void removeGroups() {
        for (Group group : new HashSet<>(groups)) {
            removeGroup(group);
        }
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public TeacherStatus getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(TeacherStatus teacherStatus) {
        this.teacherStatus = teacherStatus;
    }
}
