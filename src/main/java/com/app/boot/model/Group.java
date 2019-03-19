package com.app.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long groupId;
	private String groupName;
	private String description;
	private String hashCode;

	public Group(long groupId, String groupName, String description, String hashCode) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.description = description;
		this.hashCode = hashCode;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

}
