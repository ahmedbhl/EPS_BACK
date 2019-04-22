package com.app.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	public List<Group> getGroupBygroupName(String groupName);

}
