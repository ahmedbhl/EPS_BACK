package com.app.boot.dto;

import java.util.List;
import java.util.Set;

import com.app.boot.model.Post;
import com.app.boot.model.Professor;
import com.app.boot.model.Student;

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
@ApiModel(description = "${swagger.group-dto}")
public class GroupDTO {

	@ApiModelProperty(value = "${swagger.group-dto.id}")
	private Long id;

	@ApiModelProperty(value = "${swagger.group-dto.groupName}")
	private String groupName;

	@ApiModelProperty(value = "${swagger.group-dto.description}")
	private String description;

	@ApiModelProperty(value = "${swagger.group-dto.hashCode}")
	private String hashCode;

	@ApiModelProperty(value = "${swagger.group-dto.posts}")
	private List<Post> posts;

	@ApiModelProperty(value = "${swagger.group-dto.professors}")
	private Set<Professor> professors;

	@ApiModelProperty(value = "${swagger.group-dto.students}")
	private Set<Student> students;

}
