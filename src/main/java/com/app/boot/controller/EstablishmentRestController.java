package com.app.boot.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.boot.model.Establishment;
import com.app.boot.model.User;
import com.app.boot.service.IServiceEstablishment;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/establishments")
@SwaggerDefinition(tags = {
		@Tag(name = "EstablishmentRestController", description = "${swagger.establishment-rest-controller.description}") })
@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 408, message = "Request Time-out"),
		@ApiResponse(code = 500, message = "Internal Server Error	") })
public class EstablishmentRestController {

	@Autowired
	private IServiceEstablishment serviceEstablishment;

	@Autowired
	private ModelMapper modelMapper;

	private static final String ADDING_MESSAGE = " Establishment Adding ";

	/**
	 * Delete pairing message
	 */
	private static final String DELETE_MESSAGE = "Delete Establishment";

	/**
	 * Update pairing message
	 */
	private static final String UPDATE_MESSAGE = "Update Establishment";

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Establishment> getAll() {

		List<Establishment> establishments = serviceEstablishment.getAll();
		return null;
	}

	
	
}
