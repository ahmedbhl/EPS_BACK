package com.app.boot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.User;
import com.app.boot.repository.UserReposiroty;
import com.app.boot.service.IServiceUser;

@Service
@Transactional
public class ServiceUser implements IServiceUser {

	@Autowired
	private UserReposiroty userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceUser#findAll
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceUser#getUserByid
	 */
	@Override
	public Optional<User> getUserByid(Long id) {
		return userRepository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceUser#createUser
	 */
	@Override
	public User createUser(User user) {
		String encryptPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptPassword);
		return userRepository.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceUser#updateUser
	 */
	@Override
	public User updateUser(User user) {
		User updatedUser = userRepository.findById(user.getId())
				.orElseThrow(() -> new CodeOperationException(CodeOperationException.CodeError.CODE_NOT_FOUND.name(),
						user.getId().toString()));
		return userRepository.save(updatedUser);
	}

}
