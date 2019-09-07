package com.app.boot.service;

import java.util.List;
import java.util.Optional;

import com.app.boot.model.Professor;

public interface IServiceProfessor {

	/**
	 * Create new professor
	 * 
	 * @param professor
	 * @return
	 */
	Professor createProfessor(Professor professor);

	/**
	 * Update the professor
	 * 
	 * @param professor
	 * @return
	 */
	Professor updateProfessor(Professor professor);

	/**
	 * Get the Professor ID
	 * 
	 * @param id
	 * @return the Professor by ID
	 */
	Optional<Professor> getProfessorByid(Long id);

	/**
	 * Get all Professors
	 * 
	 * @return
	 */
	List<Professor> getAllProfessor();
}
