package com.app.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	public List<Course> getCourseBycourseName(String courseName);

}
