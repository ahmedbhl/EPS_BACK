package com.app.boot.dto;

import java.util.Date;
import java.util.Set;

import com.app.boot.model.Role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
