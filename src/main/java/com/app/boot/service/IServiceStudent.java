package com.app.boot.service;

import java.util.Optional;

import com.app.boot.model.Student;

public interface IServiceStudent {

	/**
	 * Create new student
	 * 
	 * @param student
	 * @return
	 */
	Student createStudent(Student student);

	/**
	 * Update the Student
	 * 
	 * @param Student
	 * @return
	 */
	Student updateStudent(Student student);

	/**
	 * Get the Student ID
	 * 
	 * @param id
	 * @return the Student by ID
	 */
	Optional<Student> getStudentByid(Long id);
}
