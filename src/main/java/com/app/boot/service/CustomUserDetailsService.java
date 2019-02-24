package com.app.boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.model.CustomUserDetails;
import com.app.boot.model.User;
import com.app.boot.repository.UserReposiroty;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserReposiroty userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.getUserByEmail(email);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Email Not Found"));
		return optionalUser.map(CustomUserDetails::new).get();
	}

}
