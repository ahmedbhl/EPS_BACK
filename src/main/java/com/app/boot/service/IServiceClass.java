package com.app.boot.service;

import java.util.List;
import java.util.Optional;

import com.app.boot.model.Class;

public interface IServiceClass {
	public List<Class> getAll();

	public Class Create(Class classEntity);

	public Class Update(Class classEntity);

	public void Delete(Class classEntity);

	public void DeleteById(Long id);

	public Optional<Class> getClassById(Long id);

	public List<Class> getClassByclassName(String className);

}
