package com.app.boot.service;

import java.util.List;
import java.util.Optional;

import com.app.boot.model.Group;

public interface IServiceGroup {

	public List<Group> getAll();

	public Group Create(Group group);

	public Group Update(Group group);

	public void Delete(Group group);

	public void DeleteById(Long id);

	public Optional<Group> getGroupById(Long id);

	public List<Group> getGroupBygroupName(String groupName);

}
