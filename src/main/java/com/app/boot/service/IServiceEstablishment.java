package com.app.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.model.Establishment;

@Service
@Transactional
public interface IServiceEstablishment {

	public List<Establishment> getAll();

	public Establishment Create(Establishment establishment);

	public Establishment Update(Establishment establishment);

	public void Delete(Establishment establishment);
	
	
	public Establishment DeleteById(Long id);

}
