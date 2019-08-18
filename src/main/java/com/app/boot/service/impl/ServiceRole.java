package com.app.boot.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.boot.model.Role;
import com.app.boot.repository.RoleRepository;
import com.app.boot.service.IServiceRole;

@Service
@Transactional
public class ServiceRole implements IServiceRole {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role getRoleByName(String name) {
		return roleRepository.getRoleByName(name);
	}

}
