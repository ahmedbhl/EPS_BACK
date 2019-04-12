package com.app.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.boot.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	public List<Course> getCourseBycourseName(String courseName);


}
