package com.app.boot.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.boot.model.Course;
import com.app.boot.repository.CourseRepository;
import com.app.boot.service.IServiceCourse;

@Service
@Transactional
public class ServiceImplCourse implements IServiceCourse {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> getAll() {
		return courseRepository.findAll();
	}

	@Override
	public Course Create(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course Update(Course course) {
		return courseRepository.saveAndFlush(course);
	}

	@Override
	public void Delete(Course course) {
		courseRepository.delete(course);
	}

	@Override
	public void DeleteById(Long id) {
		courseRepository.deleteById(id);
	}

	@Override
	public Optional<Course> getCourseById(Long id) {
		return courseRepository.findById(id);
	}

	@Override
	public List<Course> getCourseBycourseName(String courseName) {
		return courseRepository.getCourseBycourseName(courseName);
	}

}
