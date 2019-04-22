package com.app.boot.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.boot.model.Group;
import com.app.boot.repository.GroupRepository;
import com.app.boot.service.IServiceGroup;

@Service
@Transactional
public class ServiceImplGroup implements IServiceGroup {
	@Autowired
	private GroupRepository groupRepository;

	@Override
	public List<Group> getAll() {
		return groupRepository.findAll();
	}

	@Override
	public Group Create(Group group) {
		return groupRepository.save(group);
	}

	@Override
	public Group Update(Group group) {
		return groupRepository.saveAndFlush(group);
	}

	@Override
	public void Delete(Group group) {
		groupRepository.delete(group);
	}

	@Override
	public void DeleteById(Long id) {
		groupRepository.deleteById(id);
	}

	@Override
	public Optional<Group> getGroupById(Long id) {
		return groupRepository.findById(id);
	}

	@Override
	public List<Group> getGroupBygroupName(String groupName) {
		return groupRepository.getGroupBygroupName(groupName);
	}

}
