package com.app.boot.service;

import java.util.List;
import java.util.Optional;

import com.app.boot.model.Level;

public interface IServiceLevel {

	public List<Level> getAll();

	public Level Create(Level level);

	public Level Update(Level level);

	public void Delete(Level level);

	public void DeleteById(Long id);

	public Optional<Level> getLevelById(Long id);

	public Optional<Level> getLevelByName(String levelName);
}
