package com.app.boot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.model.Level;
import com.app.boot.repository.LevelRepository;
import com.app.boot.service.IServiceLevel;

@Service
@Transactional
public class ServiceImplLevel implements IServiceLevel {

	private LevelRepository levelRepository;

	@Override
	public List<Level> getAll() {
		return levelRepository.findAll();
	}

	@Override
	public Level Create(Level level) {
		return levelRepository.save(level);
	}

	@Override
	public Level Update(Level level) {
		return levelRepository.saveAndFlush(level);
	}

	@Override
	public void Delete(Level level) {
		levelRepository.delete(level);
	}

	@Override
	public void DeleteById(Long id) {
		levelRepository.deleteById(id);
	}

	@Override
	public Optional<Level> getLevelById(Long id) {
		return levelRepository.findById(id);
	}

	@Override
	public List<Level> getLevelBylevelName(String levelName) {

		return levelRepository.getLevelBylevelName(levelName);

	}

}
