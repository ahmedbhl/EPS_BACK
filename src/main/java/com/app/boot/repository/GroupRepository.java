package com.app.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.boot.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

	public List<Group> getGroupBygroupName(String groupName);

}
