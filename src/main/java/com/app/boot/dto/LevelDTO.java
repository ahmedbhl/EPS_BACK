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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}

	public Set<Field> getFields() {
		return fields;
	}

	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}

}
