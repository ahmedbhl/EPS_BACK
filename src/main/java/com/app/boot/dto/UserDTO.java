package com.app.boot.dto;

import java.util.Date;
import java.util.Set;

import com.app.boot.model.Role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "${swagger.user-dto}")
public class UserDTO {

	/*
	 * Id
	 */
	@ApiModelProperty(value = "${swagger.user-dto.id}")
	private Long id;

	/*
	 * User email
	 */
	@ApiModelProperty(value = "${swagger.user-dto.email}")
	private String email;

	/*
	 * User Roles
	 */
	@ApiModelProperty(value = "${swagger.user-dto.roles}")
	private Set<Role> roles;

	/*
	 * User First Name
	 */
	@ApiModelProperty(value = "${swagger.user-dto.firstName}")
	private String firstName;

	/*
	 * User Family Name
	 */
	@ApiModelProperty(value = "${swagger.user-dto.familyName}")
	private String lastName;

	/*
	 * Year Of the Registration
	 */
	@ApiModelProperty(value = "${swagger.user-dto.yearOfRegistration}")
	private Date yearOfRegistration;

	/*
	 * User Phone Number
	 */
	@ApiModelProperty(value = "${swagger.user-dto.phoneNumber}")
	private String phoneNumber;

	/*
	 * User profil Picture
	 */
	@ApiModelProperty(value = "${swagger.user-dto.profilePicture}")
	private String profilePicture;

	/*
	 * Sex of the user
	 */
	@ApiModelProperty(value = "${swagger.user-dto.sex}")
	private String sex;

	/*
	 * User Adresse
	 */
	@ApiModelProperty(value = "${swagger.user-dto.address}")
	private String address;

	/*
	 * Creation Date
	 */
	@ApiModelProperty(value = "${swagger.user-dto.creationDate}")
	private Date dateOfRegistration;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getYearOfRegistration() {
		return yearOfRegistration;
	}

	public void setYearOfRegistration(Date yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

}
