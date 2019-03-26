package com.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.Establishment;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

}
