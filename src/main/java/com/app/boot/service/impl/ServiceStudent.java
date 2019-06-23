package com.app.boot.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Student;
import com.app.boot.repository.StudentRepository;
import com.app.boot.service.IServiceStudent;

@Service
@Transactional
public class ServiceStudent implements IServiceStudent {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceStudent#createStudent
	 */
	@Override
	public Student createStudent(Student student) {
		String encryptPassword = passwordEncoder.encode(student.getPassword());
		student.setPassword(encryptPassword);
		return studentRepository.save(student);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceStudent#updateStudent
	 */
	@Override
	public Student updateStudent(Student student) {
		Student updatedStudent = studentRepository.findById(student.getId()).get();
		if (updatedStudent != null) {
			if (student != null && student.getPassword() != null) {
				String encryptPassword = passwordEncoder.encode(student.getPassword());
				student.setPassword(encryptPassword);
			} else {
				student.setPassword(updatedStudent.getPassword());
			}
			return studentRepository.save(student);
		} else {
			throw new CodeOperationException(CodeOperationException.CodeError.CODE_NOT_FOUND.name(),
					student.getId().toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceStudent#getStudentByid
	 */
	@Override
	public Optional<Student> getStudentByid(Long id) {
		return studentRepository.findById(id);
	}
}
