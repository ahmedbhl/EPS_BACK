package com.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.boot.model.Field;

public interface FieldRepository extends JpaRepository<Field, Long>{

}
