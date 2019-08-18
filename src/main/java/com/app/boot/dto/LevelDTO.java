package com.app.boot.dto;

import com.app.boot.model.Establishment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
@ApiModel(description = "${swagger.level-dto}")
public class LevelDTO {

	@ApiModelProperty(value = "${swagger.level-dto.id}")
	private Long id;

	@ApiModelProperty(value = "${swagger.level-dto.levelName}")
	private String levelName;

	@ApiModelProperty(value = "${swagger.level-dto.description}")
	private String description;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ApiModelProperty(value = "${swagger.level-dto.establishment}")
	private Establishment establishment;
}
