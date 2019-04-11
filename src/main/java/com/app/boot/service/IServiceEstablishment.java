package com.app.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.model.Establishment;


public interface IServiceEstablishment {

	public List<Establishment> getAll();

	public Establishment Create(Establishment establishment);

	public Establishment Update(Establishment establishment);

	public void Delete(Establishment establishment);

	public void DeleteById(Long id);

	public Optional<Establishment> getEstablishmentById(Long id);

}
