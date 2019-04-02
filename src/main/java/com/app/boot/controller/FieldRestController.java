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

import com.app.boot.dto.FieldDTO;
import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Field;
import com.app.boot.service.IServiceField;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import net.logstash.logback.marker.Markers;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/fields")
@SwaggerDefinition(tags = {
		@Tag(name = "FieldRestController", description = "${swagger.field-rest-controller.description}") })
@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 408, message = "Request Time-out"),
		@ApiResponse(code = 500, message = "Internal Server Error	") })
public class FieldRestController {

	@Autowired
	private IServiceField serviceField;

	@Autowired
	private ModelMapper modelMapper;

	private static final String ADDING_MESSAGE = " Field Adding ";

	/**
	 * Delete pairing message
	 */
	private static final String DELETE_MESSAGE = "Delete Field";

	/**
	 * Update pairing message
	 */
	private static final String UPDATE_MESSAGE = "Update Field";

	/**
	 * Logger
	 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(FieldRestController.class);

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.field-rest-controller.getAllFields.value}", notes = "${swagger.field-rest-controller.getAllFields.notes}")
	public ResponseEntity<List<FieldDTO>> getAll() {
		List<Field> fields = serviceField.getAll();
		List<FieldDTO> fieldDTO = fields.stream().map(field -> modelMapper.map(field, FieldDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(fieldDTO);
	}

	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "${swagger.field-rest-controller.createField.value}", notes = "${swagger.field-rest-controller.createField.notes}")
	public ResponseEntity<FieldDTO> createField(
			@ApiParam(value = "${swagger.field-rest-controller.createField.field}", required = true) @Valid @RequestBody FieldDTO fieldDTO) {
		// Map to model
		Field field = modelMapper.map(fieldDTO, Field.class);
		final FieldDTO newfieldDTO;
		try {
			// Save the new Field
			Field createdField = serviceField.Create(field);
			// Map to DTO
			newfieldDTO = modelMapper.map(createdField, FieldDTO.class);
			logFieldInfo(createdField, ADDING_MESSAGE);
		} catch (CodeOperationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newfieldDTO);
	}

	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.field-rest-controller.update.value}", notes = "${swagger.field-rest-controller.update.notes}")
	public ResponseEntity<FieldDTO> updateField(
			@ApiParam(value = "${swagger.field-rest-controller.update.field}") @RequestBody FieldDTO fieldDTO,
			@ApiParam(value = "${swagger.field-rest-controller.update.field.id}") @PathVariable("id") Long id) {
		// Map with model
		Field field = modelMapper.map(fieldDTO, Field.class);
		final FieldDTO updateField;
		try {

			// Update the field
			Field updatedField = serviceField.Update(field);
			// Map to dto
			updateField = modelMapper.map(updatedField, FieldDTO.class);
			logFieldInfo(updatedField, UPDATE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updateField);
	}

	@ResponseBody
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.field-rest-controller.delete.value}", notes = "${swagger.field-rest-controller.delete.notes}")
	public ResponseEntity<Field> delete(
			@ApiParam(value = "${swagger.field-rest-controller.delete}", required = true) @PathVariable("id") Long id) {

		final Field field = serviceField.getFieldById(id).get();
		try {
			if (field == null) {
				return ResponseEntity.notFound().build();
			}
			serviceField.Delete(field);
			;
			logFieldInfo(field, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(field);
	}

	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.field-rest-controller.deleteById.value}", notes = "${swagger.field-rest-controller.deleteById.notes}")
	public ResponseEntity<Field> deleteById(
			@ApiParam(value = "${swagger.field-rest-controller.deleteById.id}", required = true) @PathVariable("id") Long id) {

		final Field field = serviceField.getFieldById(id).get();
		try {
			if (field == null) {
				return ResponseEntity.notFound().build();
			}
			serviceField.DeleteById(id);
			logFieldInfo(field, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(field);
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.field-rest-controller.getById.value}", notes = "${swagger.field-rest-controller.getById.notes}")
	public ResponseEntity<Field> getFieldById(
			@ApiParam(value = "${swagger.field-rest-controller.getById.id}", required = true) @PathVariable("id") Long id) {
		final FieldDTO newFieldDTO;

		final Field field = serviceField.getFieldById(id).get();
		try {
			if (field == null) {
				return ResponseEntity.notFound().build();
			}
			newFieldDTO = modelMapper.map(field, FieldDTO.class);

			ResponseEntity.ok(newFieldDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(field);
	}

	@ResponseBody
	@GetMapping(value = "/{fieldName}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.field-rest-controller.getFieldByfieldName.value}", notes = "${swagger.field-rest-controller.getFieldByfieldName.notes}")
	public ResponseEntity<List<Field>> getFieldByfieldName(
			@ApiParam(value = "${swagger.field-rest-controller.getFieldByfieldName.fieldName}", required = true) @PathVariable("fieldName") String fieldName) {
		// final FieldDTO newFieldDTO;
		List<Field> fields = serviceField.getFieldByfieldName(fieldName);

		try {
			if (fieldName == null) {
				return ResponseEntity.notFound().build();
			}
			List<FieldDTO> fieldDTO = fields.stream().map(field -> modelMapper.map(field, FieldDTO.class))
					.collect(Collectors.toList());

			ResponseEntity.ok(fieldDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(fields);
	}

	private void logFieldInfo(final Field field, final String message) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(Markers.append("app_field_id", field.getId())
					.and(Markers.append("app_field_fieldName", field.getFieldName())), message);
		}
	}

}
