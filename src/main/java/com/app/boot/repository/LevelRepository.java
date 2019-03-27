package com.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.boot.model.Level;

public interface LevelRepository extends JpaRepository<Level, Long> {

}
