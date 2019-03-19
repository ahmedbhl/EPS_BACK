package com.app.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Class {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long classId;
	private String className;
	private String description;
	private String invitationCode;

	public Class(long classId, String className, String description, String invitationCode) {
		super();
		this.classId = classId;
		this.className = className;
		this.description = description;
		this.invitationCode = invitationCode;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

}
