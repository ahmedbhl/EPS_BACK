package com.app.boot.dto;

import com.app.boot.model.Establishment;

import io.swagger.annotations.ApiModel;
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

	private Long id;

	private String className;

	private String description;

	private String invitationCode;

	private Establishment establishment;

}
