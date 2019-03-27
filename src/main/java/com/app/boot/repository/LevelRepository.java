package com.app.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.boot.model.Level;

public interface LevelRepository extends JpaRepository<Level, Long> {

	Optional<Level> findOne(String levelName);

}
