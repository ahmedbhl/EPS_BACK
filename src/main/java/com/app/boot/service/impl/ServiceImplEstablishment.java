package com.app.boot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.model.Establishment;
import com.app.boot.repository.EstablishmentRepository;
import com.app.boot.service.IServiceEstablishment;

@Service
@Transactional
public class ServiceImplEstablishment implements IServiceEstablishment {

	@Autowired
	private EstablishmentRepository establishmentRepository;

	@Override
	public List<Establishment> getAll() {
		return establishmentRepository.findAll();
	}

	@Override
	public Establishment Create(Establishment establishment) {
		return establishmentRepository.save(establishment);
	}

	@Override
	public Establishment Update(Establishment establishment) {
		return establishmentRepository.saveAndFlush(establishment);
	}

	@Override
	public void delete(Establishment establishment) {
		establishmentRepository.delete(establishment);

	}

	@Override
	public void deleteById(Long id) {
		establishmentRepository.deleteById(id);

	}

	@Override
	public Optional<Establishment> getEstablishmentById(Long id) {
		return establishmentRepository.findById(id);
	}

	@Override
	public List<Establishment> getEstablishmentByestablishmentName(String establishmentName) {
		return establishmentRepository.getEstablishmentByestablishmentName(establishmentName);
	}

}
