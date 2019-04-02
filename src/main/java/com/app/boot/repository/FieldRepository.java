package com.app.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.boot.model.Field;

public interface FieldRepository extends JpaRepository<Field, Long>{

	public List<Field> getFieldByfieldName(String fieldName);

}
