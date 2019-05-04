package com.app.boot.service;

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
}
