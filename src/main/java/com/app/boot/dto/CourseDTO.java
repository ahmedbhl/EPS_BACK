package com.app.boot.dto;

import java.util.Set;

import com.app.boot.model.Class;
import com.app.boot.model.Professor;

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
@ApiModel(description = "${swagger.course-dto}")
public class CourseDTO {

	@ApiModelProperty(value = "${swagger.course-dto.id}")
	private Long id;
	
	@ApiModelProperty(value = "${swagger.course-dto.courseName}")
	private String courseName;
	
	@ApiModelProperty(value = "${swagger.course-dto.description}")
	private String description;

	@ApiModelProperty(value = "${swagger.course-dto.classe}")
	private Class classe;

	@ApiModelProperty(value = "${swagger.course-dto.professors}")
	private Set<Professor> professors;

}
