package com.app.boot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Level;
import com.app.boot.repository.LevelRepository;
import com.app.boot.service.IServiceLevel;

@Service
@Transactional
public class ServiceImplLevel implements IServiceLevel {

	@Autowired
	private LevelRepository levelRepository;

	@Override
	public List<Level> getAll() {
		return levelRepository.findAll();
	}

	@Override
	public Level create(Level level) {
		return levelRepository.save(level);
	}

	@Override
	public Level update(Level level) {
		return levelRepository.saveAndFlush(level);
	}

	@Override
	public Level delete(Level level) {
		Optional<Level> deletedLevel = levelRepository.findById(level.getId());
		if (!deletedLevel.isPresent()) {
			throw new CodeOperationException(CodeOperationException.CodeError.CODE_NOT_FOUND.name(),
					level.getLevelName());
		}
		levelRepository.delete(deletedLevel.get());
		return deletedLevel.get();
	}

	@Override
	public Level deleteById(Long id) {
		Optional<Level> deletedLevel = levelRepository.findById(id);
		if (!deletedLevel.isPresent()) {
			throw new CodeOperationException(CodeOperationException.CodeError.CODE_NOT_FOUND.name(), id.toString());
		}
		levelRepository.delete(deletedLevel.get());
		return deletedLevel.get();
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
