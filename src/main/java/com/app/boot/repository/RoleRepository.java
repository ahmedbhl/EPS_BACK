package com.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
