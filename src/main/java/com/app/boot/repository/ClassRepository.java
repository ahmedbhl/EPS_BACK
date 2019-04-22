package com.app.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
	public List<Class> getClassByclassName(String className);

}
