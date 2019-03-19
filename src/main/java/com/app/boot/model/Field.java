package com.app.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Field {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fieldId;
	private String fieldName;
	private String description;

	public Field(long fieldId, String fieldName, String description) {
		super();
		this.fieldId = fieldId;
		this.fieldName = fieldName;
		this.description = description;
	}

	public long getFieldId() {
		return fieldId;
	}

	public void setFieldId(long fieldId) {
		this.fieldId = fieldId;
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

}
