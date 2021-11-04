package com.gbsfo.tutor.springboottutor.repository;

import com.gbsfo.tutor.springboottutor.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByLastName(String lastName);
}
