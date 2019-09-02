package com.app.boot.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.app.boot.dto.EstablishmentDTO;
import com.app.boot.model.Establishment;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class EstablishementMapper extends CustomMapper<Establishment, EstablishmentDTO> {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	@Override
	public void mapAtoB(Establishment a, EstablishmentDTO b, MappingContext context) {
		b.setId(a.getId());
		b.setAdministration(a.getAdministration());
		b.setDescription(a.getDescription());
		b.setEstablishmentName(a.getEstablishmentName());
		b.setLocation(a.getLocation());
		b.setPhotos(a.getPhotos());
		b.setYearOfFoundation(String.valueOf(a.getYearOfFoundation()));

	}

	@Override
	public void mapBtoA(EstablishmentDTO b, Establishment a, MappingContext context) {
		a.setId(b.getId());
		a.setDescription(b.getDescription());
		a.setEstablishmentName(b.getEstablishmentName());
		a.setLocation(b.getLocation());
		a.setPhotos(b.getPhotos());
		a.setYearOfFoundation(LocalDateTime.parse(b.getYearOfFoundation(),formatter));
		a.setAdministration(b.getAdministration());

	}
}
