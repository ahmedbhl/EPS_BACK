package com.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.boot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
