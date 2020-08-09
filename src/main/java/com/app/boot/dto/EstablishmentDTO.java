package com.app.boot.dto;

import com.app.boot.model.Administration;

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
@ApiModel(description = "${swagger.establishment-dto}")
public class EstablishmentDTO {

	@ApiModelProperty(value = "${swagger.establishment-dto.id}")
	private Long id;

	@ApiModelProperty(value = "${swagger.establishment-dto.establishmentName}")
	private String establishmentName;

	@ApiModelProperty(value = "${swagger.establishment-dto.description}")
	private String description;

	@ApiModelProperty(value = "${swagger.establishment-dto.yearOfFoundation}")
	private String yearOfFoundation;

	@ApiModelProperty(value = "${swagger.establishment-dto.location}")
	private String location;

	@ApiModelProperty(value = "${swagger.establishment-dto.photos}")
	private String photos;

	@ApiModelProperty(value = "${swagger.establishment-dto.administration}")
	private Administration administration;
}
