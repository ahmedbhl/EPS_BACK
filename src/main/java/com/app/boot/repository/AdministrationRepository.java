package com.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.Administration;

@Repository
public interface AdministrationRepository extends JpaRepository<Administration, Long> {

}
