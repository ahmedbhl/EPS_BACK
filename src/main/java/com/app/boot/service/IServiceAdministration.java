package com.app.boot.service;

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
}
