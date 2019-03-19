package com.app.boot.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Professor extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String firstName;

	private String familyName;

	private String gender;

	public Professor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Professor(Long id, String email, String password, Set<Role> roles, Date yearOfRegistration,
			String phoneNumber, String profilePicture, String address, boolean status) {
		super(id, email, password, roles, yearOfRegistration, phoneNumber, profilePicture, address, status);
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
