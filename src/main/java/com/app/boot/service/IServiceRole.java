package com.app.boot.service;

import com.app.boot.model.Role;

public interface IServiceRole {

	/**
	 * Get the Role By name
	 * 
	 * @param name
	 * @return
	 */
	Role getRoleByName(String name);

}
