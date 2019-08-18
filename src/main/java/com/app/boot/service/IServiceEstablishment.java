package com.app.boot.service;

import java.util.List;
import java.util.Optional;

import com.app.boot.model.Establishment;

public interface IServiceEstablishment {

	public List<Establishment> getAll();

	public Establishment Create(Establishment establishment);

	public Establishment Update(Establishment establishment);

	public void delete(Establishment establishment);

	public void deleteById(Long id);

	public Optional<Establishment> getEstablishmentById(Long id);

	public List<Establishment> getEstablishmentByestablishmentName(String establishmentName);

}
