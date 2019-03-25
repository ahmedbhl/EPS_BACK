package com.app.boot.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "${swagger.establishment-dto}")
public class EstablishmentDTO {

	@ApiModelProperty(value = "${swagger.establishment-dto.id}")
	private Long id;

	@ApiModelProperty(value = "${swagger.establishment-dto.establishmentName}")
	private String establishmentName;

	@ApiModelProperty(value = "${swagger.establishment-dto.description}")
	private String description;

	@ApiModelProperty(value = "${swagger.establishment-dto.yearOfFoundation}")
	private Date yearOfFoundation;

	@ApiModelProperty(value = "${swagger.establishment-dto.location}")
	private String location;

	@ApiModelProperty(value = "${swagger.establishment-dto.photos}")
	private String photos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstablishmentName() {
		return establishmentName;
	}

	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getYearOfFoundation() {
		return yearOfFoundation;
	}

	public void setYearOfFoundation(Date yearOfFoundation) {
		this.yearOfFoundation = yearOfFoundation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

}
