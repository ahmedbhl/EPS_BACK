package com.app.boot.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.boot.dto.ClassDTO;
import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Class;
import com.app.boot.service.IServiceClass;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import net.logstash.logback.marker.Markers;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/classes")
@SwaggerDefinition(tags = {
		@Tag(name = "ClassRestController", description = "${swagger.class-rest-controller.description}") })
@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 408, message = "Request Time-out"),
		@ApiResponse(code = 500, message = "Internal Server Error	") })

public class ClassRestController {

	@Autowired
	private IServiceClass serviceClass;

	@Autowired
	private ModelMapper modelMapper;

	private static final String ADDING_MESSAGE = " Class Adding ";

	/**
	 * Delete pairing message
	 */
	private static final String DELETE_MESSAGE = "Delete Class";

	/**
	 * Update pairing message
	 */
	private static final String UPDATE_MESSAGE = "Update Class";

	/**
	 * Logger
	 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassRestController.class);

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.class-rest-controller.getAllClasses.value}", notes = "${swagger.class-rest-controller.getAllClasses.notes}")
	public ResponseEntity<List<ClassDTO>> getAllClasses() {
		List<Class> classesEntities = serviceClass.getAll();
		List<ClassDTO> classesDTO = classesEntities.stream()
				.map(classEntity -> modelMapper.map(classEntity, ClassDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok(classesDTO);
	}

	/**
	 * 
	 * @param classDTO
	 * @return
	 */
	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "${swagger.class-rest-controller.createClass.value}", notes = "${swagger.class-rest-controller.createClass.notes}")
	public ResponseEntity<ClassDTO> createClass(
			@ApiParam(value = "${swagger.class-rest-controller.createClass.class}", required = true) @Valid @RequestBody ClassDTO classDTO) {
		// Map to model
		Class classEntity = modelMapper.map(classDTO, Class.class);
		final ClassDTO newClassDTO;
		try {
			// Save the new Class
			Class createdClass = serviceClass.Create(classEntity);
			// Map to DTO
			newClassDTO = modelMapper.map(createdClass, ClassDTO.class);
			logClassInfo(createdClass, ADDING_MESSAGE);
		} catch (CodeOperationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newClassDTO);
	}

	/**
	 * Update the Class
	 * 
	 * @param ClassDTO
	 * @param id
	 * @return
	 */
	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.class-rest-controller.update.value}", notes = "${swagger.class-rest-controller.update.notes}")
	public ResponseEntity<ClassDTO> updateClass(
			@ApiParam(value = "${swagger.class-rest-controller.update.class}") @RequestBody ClassDTO classDTO,
			@ApiParam(value = "${swagger.class-rest-controller.update.class.id}") @PathVariable("id") Long id) {
		// Map with model
		Class classEntity = modelMapper.map(classDTO, Class.class);
		final ClassDTO updateClass;
		try {
			// Update the class
			Class updatedClass = serviceClass.Update(classEntity);
			// Map to dto
			updateClass = modelMapper.map(updatedClass, ClassDTO.class);
			logClassInfo(updatedClass, UPDATE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updateClass);
	}

	@ResponseBody
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.class-rest-controller.delete.value}", notes = "${swagger.class-rest-controller.delete.notes}")
	public ResponseEntity<Class> delete(
			@ApiParam(value = "${swagger.class-rest-controller.delete}", required = true) @PathVariable("id") Long id) {

		final Class classEntity = serviceClass.getClassById(id).get();
		try {
			if (classEntity == null) {
				return ResponseEntity.notFound().build();
			}
			serviceClass.Delete(classEntity);
			;
			logClassInfo(classEntity, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(classEntity);
	}

	/**
	 * Delete the Class having the passed id.
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.class-rest-controller.delete.value}", notes = "${swagger.class-rest-controller.delete.notes}")
	public ResponseEntity<Class> deleteById(
			@ApiParam(value = "${swagger.class-rest-controller.delete.id}", required = true) @PathVariable("id") Long id) {

		final Class classEntity = serviceClass.getClassById(id).get();
		try {
			if (classEntity == null) {
				return ResponseEntity.notFound().build();
			}
			serviceClass.DeleteById(id);
			logClassInfo(classEntity, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(classEntity);
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.class-rest-controller.getById.value}", notes = "${swagger.class-rest-controller.getById.notes}")
	public ResponseEntity<Class> getClassById(
			@ApiParam(value = "${swagger.class-rest-controller.getById.id}", required = true) @PathVariable("id") Long id) {
		final ClassDTO newClassDTO;

		final Class classEntity = serviceClass.getClassById(id).get();
		try {
			if (classEntity == null) {
				return ResponseEntity.notFound().build();
			}
			newClassDTO = modelMapper.map(classEntity, ClassDTO.class);

			ResponseEntity.ok(newClassDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(classEntity);
	}

	@ResponseBody
	@GetMapping(value = "/{className}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.class-rest-controller.getClassByclassName.value}", notes = "${swagger.class-rest-controller.getClassByclassName.notes}")
	public ResponseEntity<List<Class>> getClassByclassName(
			@ApiParam(value = "${swagger.class-rest-controller.getClassByclassName.className}", required = true) @PathVariable("className") String className) {
		// final ClassDTO newClassDTO;
		List<Class> classes = serviceClass.getClassByclassName(className);

		try {
			if (className == null) {
				return ResponseEntity.notFound().build();
			}
			List<ClassDTO> classDTO = classes.stream().map(classEntity -> modelMapper.map(classEntity, ClassDTO.class))
					.collect(Collectors.toList());

			ResponseEntity.ok(classDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(classes);
	}

	/**
	 * 
	 * @param pairingModel
	 * @param message
	 */
	private void logClassInfo(final Class classEntity, final String message) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(Markers.append("app_class_id", classEntity.getId())
					.and(Markers.append("app_class_className", classEntity.getClassName()))
					.and(Markers.append("app_class_location", classEntity.getInvitationCode())), message);
		}
	}

}
