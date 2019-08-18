package com.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
