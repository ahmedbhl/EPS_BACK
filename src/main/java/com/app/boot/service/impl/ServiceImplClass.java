package com.app.boot.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.boot.model.Class;
import com.app.boot.repository.ClassRepository;
import com.app.boot.service.IServiceClass;

@Service
@Transactional
public class ServiceImplClass implements IServiceClass {

	@Autowired
	private ClassRepository classRepository;

	@Override
	public List<Class> getAll() {
		return classRepository.findAll();
	}

	@Override
	public Class Create(Class classEntity) {
		return classRepository.save(classEntity);
	}

	@Override
	public Class Update(Class classEntity) {
		return classRepository.saveAndFlush(classEntity);
	}

	@Override
	public void Delete(Class classEntity) {
		classRepository.delete(classEntity);
	}

	@Override
	public void DeleteById(Long id) {
		classRepository.deleteById(id);

	}

	@Override
	public Optional<Class> getClassById(Long id) {
		return classRepository.findById(id);
	}

	@Override
	public List<Class> getClassByclassName(String className) {
		return classRepository.getClassByclassName(className);
	}

}
