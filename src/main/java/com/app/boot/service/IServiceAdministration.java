package com.app.boot.service;

import java.util.Optional;

import com.app.boot.model.Administration;

public interface IServiceAdministration {

	/**
	 * Create new Administration
	 * 
	 * @param administration
	 * @return
	 */
	Administration createAdministration(Administration administration);

	/**
	 * Update the Administration
	 * 
	 * @param administration
	 * @return
	 */
	Administration updateAdministration(Administration administration);

	/**
	 * Get the Administration ID
	 * 
	 * @param id
	 * @return the Administration by ID
	 */
	Optional<Administration> getAdministrationByid(Long id);
}
