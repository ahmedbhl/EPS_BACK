package com.app.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.Level;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

	public List<Level> getLevelBylevelName(String levelName);
	
	
}
