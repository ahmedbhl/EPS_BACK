package com.app.boot.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Administration;
import com.app.boot.repository.AdministrationRepository;
import com.app.boot.service.IServiceAdministration;

@Service
@Transactional
public class ServiceAdministration implements IServiceAdministration {

	@Autowired
	AdministrationRepository administrationRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceAdministration#createAdministration
	 */
	@Override
	public Administration createAdministration(Administration administration) {
		String encryptPassword = passwordEncoder.encode(administration.getPassword());
		administration.setPassword(encryptPassword);
		return administrationRepository.save(administration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceAdministration#updateAdministration
	 */
	@Override
	public Administration updateAdministration(Administration administration) {
		Administration updatedAdministration = administrationRepository.findById(administration.getId()).get();
		if (updatedAdministration != null) {
			if (administration != null && administration.getPassword() != null) {
				String encryptPassword = passwordEncoder.encode(administration.getPassword());
				administration.setPassword(encryptPassword);
			} else {
				administration.setPassword(updatedAdministration.getPassword());
			}
			return administrationRepository.save(administration);
		} else {
			throw new CodeOperationException(CodeOperationException.CodeError.CODE_NOT_FOUND.name(),
					administration.getId().toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.boot.IServiceAdministration#getAdministrationByid
	 */
	@Override
	public Optional<Administration> getAdministrationByid(Long id) {
		return administrationRepository.findById(id);
	}

}
