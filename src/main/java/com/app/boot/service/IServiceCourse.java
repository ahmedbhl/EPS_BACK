package com.app.boot.service;

import java.util.List;
import java.util.Optional;

import com.app.boot.model.Course;

public interface IServiceCourse {
	
	public List<Course> getAll();

	public Course Create(Course course);

	public Course Update(Course course);

	public void Delete(Course course);

	public void DeleteById(Long id);

	public Optional<Course> getCourseById(Long id);

	public List<Course> getCourseBycourseName(String courseName);

}
