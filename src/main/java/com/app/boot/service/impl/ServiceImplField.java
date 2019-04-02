package com.app.boot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.model.Field;
import com.app.boot.repository.FieldRepository;
import com.app.boot.service.IServiceField;

@Service
@Transactional
public class ServiceImplField implements IServiceField {

	private FieldRepository fieldRepository;

	@Override
	public List<Field> getAll() {
		return fieldRepository.findAll();
	}

	@Override
	public Field Create(Field field) {
		return fieldRepository.save(field);
	}

	@Override
	public Field Update(Field field) {
		return fieldRepository.saveAndFlush(field);
	}

	@Override
	public void Delete(Field field) {
		fieldRepository.delete(field);
	}

	@Override
	public void DeleteById(Long id) {
		fieldRepository.deleteById(id);
	}

	@Override
	public Optional<Field> getFieldById(Long id) {
		return fieldRepository.findById(id);
	}

	@Override
	public List<Field> getFieldByFieldName(String fieldName) {
		return fieldRepository.getFieldByfieldName(fieldName);
	}

}
