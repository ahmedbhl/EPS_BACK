package com.app.boot.service;

import java.util.List;
import java.util.Optional;

import com.app.boot.model.User;

public interface IServiceUser {

	/**
	 * Get the list of all user
	 * 
	 * @return the list of all users
	 */
	List<User> getAllUsers();

	/**
	 * Get the user By Email
	 * 
	 * @param email
	 * @return
	 */
	User getUserByEmail(String email);

	/**
	 * Get the user ID
	 * 
	 * @param id
	 * @return the user by ID
	 */
	Optional<User> getUserByid(Long id);

	/**
	 * Create new user
	 * 
	 * @param user
	 * @return
	 */
	User createUser(User user);

	/**
	 * Update the user
	 * 
	 * @param user
	 * @return
	 */
	User updateUser(User user);

	/**
	 * Delete the User
	 * 
	 * @param id
	 * @return
	 */
	User deleteUser(Long id);

}
