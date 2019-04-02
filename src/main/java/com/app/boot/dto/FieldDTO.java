package com.app.boot.dto;

import java.util.Set;

import com.app.boot.model.Class;
import com.app.boot.model.Establishment;
import com.app.boot.model.Level;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "${swagger.field-dto}")

public class FieldDTO {

	@ApiModelProperty(value = "${swagger.field-dto.id}")
	private Long id;
	@ApiModelProperty(value = "${swagger.field-dto.fieldName}")

	private String fieldName;
	@ApiModelProperty(value = "${swagger.field-dto.description}")

	private String description;
	@ApiModelProperty(value = "${swagger.field-dto.establishment}")

	private Establishment establishment;
	@ApiModelProperty(value = "${swagger.field-dto.level}")

	private Level level;
	@ApiModelProperty(value = "${swagger.field-dto.classes}")

	private Set<Class> classes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

}
