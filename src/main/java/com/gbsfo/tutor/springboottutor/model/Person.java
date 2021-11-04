package com.gbsfo.tutor.springboottutor.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Person {
    
    @Column(name = "last_name", nullable = false, unique = true)
    protected String lastName;
    
    @Column(name = "first_name", nullable = false)
    protected String firstName;
    
    @Column(name = "birth_date", nullable = false)
    protected LocalDate birthDate;
    
    @Enumerated(value = EnumType.STRING)
    @Column(name = "sex", nullable = false)
    protected Gender gender;
    
    protected Person() {}

    protected Person(String lastName, String firstName, LocalDate birthDate, Gender gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getSex() {
        return gender;
    }

    public void setSex(Gender gender) {
        this.gender = gender;
    }
}
