package com.app.boot.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.boot.dto.UserDTO;
import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.User;
import com.app.boot.service.IServiceUser;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
@SwaggerDefinition(tags = {
		@Tag(name = "UserRestController", description = "${swagger.user-rest-controller.description}") })
@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 408, message = "Request Time-out"),
		@ApiResponse(code = 500, message = "Internal Server Error	") })
public class UserRestController {

	@Autowired
	IServiceUser userService;

	@Autowired
	ModelMapper modelMapper;

	/**
	 * Get the list of all Users
	 * 
	 * @return list of all Users
	 */
	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.user-rest-controller.getAllUsers.value}", notes = "${swagger.user-rest-controller.getAllUsers.notes}")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		List<UserDTO> usersDTO = users.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(usersDTO);
	}

	/**
	 * 
	 * @param userDTO
	 * @return
	 */
	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "${swagger.user-rest-controller.createUser.value}", notes = "${swagger.user-rest-controller.createUser.notes}")
	public ResponseEntity<UserDTO> createUser(
			@ApiParam(value = "${swagger.user-rest-controller.createUser.user}", required = true) @Valid @RequestBody UserDTO userDTO) {
		// Map to model
		User user = modelMapper.map(userDTO, User.class);
		final UserDTO newUserDTO;
		try {
			// Save the new user
			User createdUser = userService.createUser(user);
			// Map to DTO
			newUserDTO = modelMapper.map(createdUser, UserDTO.class);
		} catch (CodeOperationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newUserDTO);
	}

}
