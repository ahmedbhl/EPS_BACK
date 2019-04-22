package com.app.boot.dto;

import com.app.boot.model.Class;
import com.app.boot.model.Group;
import com.app.boot.model.User;

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
@ApiModel(description = "${swagger.post-dto}")
public class PostDTO {

	@ApiModelProperty(value = "${swagger.post-dto.id}")
	private Long id;

	@ApiModelProperty(value = "${swagger.post-dto.type}")
	private String type;

	@ApiModelProperty(value = "${swagger.post-dto.description}")
	private String description;

	@ApiModelProperty(value = "${swagger.post-dto.group}")
	private Group group;

	@ApiModelProperty(value = "${swagger.post-dto.user}")
	private User user;

	@ApiModelProperty(value = "${swagger.post-dto.classe}")
	private Class classe;
}
