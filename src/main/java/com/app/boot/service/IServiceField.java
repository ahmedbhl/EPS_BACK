package com.app.boot.service;

import java.util.List;
import java.util.Optional;

import com.app.boot.model.Field;

public interface IServiceField {

	public List<Field> getAll();

	public Field Create(Field field);

	public Field Update(Field field);

	public void Delete(Field field);

	public void DeleteById(Long id);

	public Optional<Field> getFieldById(Long id);

	public List<Field> getFieldByFieldName(String fieldName);
}
