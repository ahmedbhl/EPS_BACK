package com.app.boot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.CustomUserDetails;
import com.app.boot.model.User;
import com.app.boot.repository.UserReposiroty;
import com.app.boot.service.IServiceUser;

@Service
@Transactional
public class ServiceImplUser implements IServiceUser, UserDetailsService {

	@Autowired
	private UserReposiroty userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.getUserByEmail(email);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		return optionalUser.map(CustomUserDetails::new).get();
	}

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
	 * @see com.app.boot.IServiceUser#findAll
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getAllAdministration() {
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
		User updateduser = userRepository.findById(user.getId()).get();
		if (updateduser != null) {
			if (user != null && user.getPassword() != null) {
				String encryptPassword = passwordEncoder.encode(user.getPassword());
				user.setPassword(encryptPassword);
			} else {
				user.setPassword(updateduser.getPassword());
			}
			return userRepository.save(user);
		} else {
			throw new CodeOperationException(CodeOperationException.CodeError.CODE_NOT_FOUND.name(),
					user.getId().toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceUser#deleteUser
	 */
	@Override
	public User deleteUser(Long id) {
		User deletedUser = userRepository.findById(id)
				.orElseThrow(() -> new CodeOperationException(CodeOperationException.CodeError.CODE_NOT_FOUND.name(),
						id.toString()));
		userRepository.delete(deletedUser);
		return deletedUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceUser#getUserByEmail
	 */
	@Override
	public User getUserByEmail(String email) {
		Optional<User> optionalUser = userRepository.getUserByEmail(email);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Email Not Found"));
		return optionalUser.map(CustomUserDetails::new).get();
	}

	@Override
	public User acivateUser(Long id) {
		Optional<User> activatedUser = userRepository.findById(id);
		if (activatedUser.isPresent()) {
			activatedUser.get().setEnabled(true);
			return userRepository.save(activatedUser.get());
		} else {
			throw new CodeOperationException(CodeOperationException.CodeError.CODE_NOT_FOUND.name(), id.toString());
		}
	}

	@Override
	public User resetUserPassword(User user) {
		Optional<User> resetedUserPassword = userRepository.findById(user.getId());
		if (resetedUserPassword.isPresent()) {
			String encryptPassword = passwordEncoder.encode(user.getPassword());
			resetedUserPassword.get().setPassword(encryptPassword);
			return userRepository.save(resetedUserPassword.get());
		} else {
			throw new CodeOperationException(CodeOperationException.CodeError.CODE_NOT_FOUND.name(),
					user.getId().toString());
		}
	}

	@Override
	public User updateUserStat(Long id) {
		Optional<User> activatedUser = userRepository.findById(id);
		if (activatedUser.isPresent()) {
			activatedUser.get().setEnabled(!activatedUser.get().isEnabled());
			return userRepository.save(activatedUser.get());
		} else {
			throw new CodeOperationException(CodeOperationException.CodeError.CODE_NOT_FOUND.name(), id.toString());
		}
	}

}
