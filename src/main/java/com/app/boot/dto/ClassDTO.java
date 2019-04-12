package com.app.boot.dto;

import com.app.boot.model.Establishment;
import com.app.boot.model.Field;

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
@ApiModel(description = "${swagger.class-dto}")
public class ClassDTO {

	@ApiModelProperty(value = "${swagger.class-dto.id}")
	private Long id;

	@ApiModelProperty(value = "${swagger.class-dto.className}")
	private String className;

	@ApiModelProperty(value = "${swagger.class-dto.description}")
	private String description;

	@ApiModelProperty(value = "${swagger.class-dto.invitationCode}")
	private String invitationCode;

	@ApiModelProperty(value = "${swagger.class-dto.establishment}")
	private Establishment establishment;

	@ApiModelProperty(value = "${swagger.class-dto.field}")
	private Field field;
}
