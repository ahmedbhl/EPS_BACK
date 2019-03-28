package com.app.boot.dto;

import java.util.Set;

import com.app.boot.model.Establishment;
import com.app.boot.model.Field;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "${swagger.level-dto}")
public class LevelDTO {

	@ApiModelProperty(value = "${swagger.level-dto.id}")
	private Long id;

	@ApiModelProperty(value = "${swagger.level-dto.levelName}")
	private String levelName;
	
	@ApiModelProperty(value = "${swagger.level-dto.description}")
	private String description;
	
	@ApiModelProperty(value = "${swagger.level-dto.establishment}")
	private Establishment establishment;

	@ApiModelProperty(value = "${swagger.level-dto.fields}")
	private Set<Field> fields;

}
