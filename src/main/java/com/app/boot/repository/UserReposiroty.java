package com.app.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.User;

@Repository
public interface UserReposiroty extends JpaRepository<User, Long> {

	Optional<User> getUserByEmail(String email);

}
