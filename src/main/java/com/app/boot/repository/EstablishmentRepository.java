package com.app.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.Establishment;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
	
	public List<Establishment> getEstablishmentByestablishmentName(String establishmentName);


}
