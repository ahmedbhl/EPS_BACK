package com.app.boot.dto;

import com.app.boot.model.Level;

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
@ApiModel(description = "${swagger.field-dto}")
public class FieldDTO {

	@ApiModelProperty(value = "${swagger.field-dto.id}")
	private Long id;

	@ApiModelProperty(value = "${swagger.field-dto.fieldName}")
	private String fieldName;

	@ApiModelProperty(value = "${swagger.field-dto.description}")
	private String description;

	@ApiModelProperty(value = "${swagger.field-dto.level}")
	private Level level;

}
