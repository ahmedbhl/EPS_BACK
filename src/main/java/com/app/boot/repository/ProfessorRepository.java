package com.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
