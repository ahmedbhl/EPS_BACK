package com.app.boot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Professor;
import com.app.boot.repository.ProfessorRepository;
import com.app.boot.service.IServiceProfessor;

@Service
@Transactional
public class ServiceProfessor implements IServiceProfessor {

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceProfessor#createProfessor
	 */
	@Override
	public Professor createProfessor(Professor professor) {
		String encryptPassword = passwordEncoder.encode(professor.getPassword());
		professor.setPassword(encryptPassword);
		return professorRepository.save(professor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceProfessor#updateProfessor
	 */
	@Override
	public Professor updateProfessor(Professor professor) {
		Professor updatedprofessor = professorRepository.findById(professor.getId()).get();
		if (updatedprofessor != null) {
			if (professor != null && professor.getPassword() != null) {
				String encryptPassword = passwordEncoder.encode(professor.getPassword());
				professor.setPassword(encryptPassword);
			} else {
				professor.setPassword(updatedprofessor.getPassword());
			}
			return professorRepository.save(professor);
		} else {
			throw new CodeOperationException(CodeOperationException.CodeError.CODE_NOT_FOUND.name(),
					professor.getId().toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceProfessor#getProfessorByid
	 */
	@Override
	public Optional<Professor> getProfessorByid(Long id) {
		return professorRepository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceProfessor#getAllProfessor
	 */
	@Override
	public List<Professor> getAllProfessor() {
		return professorRepository.findAll();
	}

}
