package com.app.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.boot.model.User;

public interface UserReposiroty extends JpaRepository<User, Long> {

	Optional<User> getUserByEmail(String email);

}
