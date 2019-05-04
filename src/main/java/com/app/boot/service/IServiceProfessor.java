package com.app.boot.service;

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
}
