package com.app.boot.service;

import java.util.List;
import java.util.Optional;

import com.app.boot.model.Level;

public interface IServiceLevel {

	public List<Level> getAll();

	public Level create(Level level);

	public Level update(Level level);

	public Level delete(Level level);

	public Level deleteById(Long id);

	public Optional<Level> getLevelById(Long id);

	public List<Level> getLevelBylevelName(String levelName);
}
