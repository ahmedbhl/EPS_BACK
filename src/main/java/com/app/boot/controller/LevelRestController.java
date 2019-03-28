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

import com.app.boot.dto.LevelDTO;
import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Level;
import com.app.boot.service.IServiceLevel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import net.logstash.logback.marker.Markers;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/levels")
@SwaggerDefinition(tags = {
		@Tag(name = "LevelRestController", description = "${swagger.level-rest-controller.description}") })
@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 408, message = "Request Time-out"),
		@ApiResponse(code = 500, message = "Internal Server Error	") })
public class LevelRestController {

	@Autowired
	private IServiceLevel serviceLevel;

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

	/**
	 * Logger
	 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(EstablishmentRestController.class);

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.level-rest-controller.getAllLevels.value}", notes = "${swagger.level-rest-controller.getAllLevels.notes}")
	public ResponseEntity<List<LevelDTO>> getAll() {
		List<Level> levels = serviceLevel.getAll();
		List<LevelDTO> levelDTO = levels.stream().map(level -> modelMapper.map(level, LevelDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(levelDTO);
	}

	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "${swagger.level-rest-controller.createLevel.value}", notes = "${swagger.level-rest-controller.createLevel.notes}")
	public ResponseEntity<LevelDTO> createLevel(
			@ApiParam(value = "${swagger.level-rest-controller.createLevel.level}", required = true) @Valid @RequestBody LevelDTO levelDTO) {
		// Map to model
		Level level = modelMapper.map(levelDTO, Level.class);
		final LevelDTO newlevelDTO;
		try {
			// Save the new Establishment
			Level createdLevel = serviceLevel.Create(level);
			// Map to DTO
			newlevelDTO = modelMapper.map(createdLevel, LevelDTO.class);
			logEstablishmentInfo(createdLevel, ADDING_MESSAGE);
		} catch (CodeOperationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newlevelDTO);
	}

	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.level-rest-controller.update.value}", notes = "${swagger.level-rest-controller.update.notes}")
	public ResponseEntity<LevelDTO> updateLevel(
			@ApiParam(value = "${swagger.level-rest-controller.update.level}") @RequestBody LevelDTO levelDTO,
			@ApiParam(value = "${swagger.level-rest-controller.update.level.id}") @PathVariable("id") Long id) {
		// Map with model
		Level level = modelMapper.map(levelDTO, Level.class);
		final LevelDTO updateLevel;
		try {

			// Update the level
			Level updatedLevel = serviceLevel.Update(level);
			// Map to dto
			updateLevel = modelMapper.map(updatedLevel, LevelDTO.class);
			logEstablishmentInfo(updatedLevel, UPDATE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updateLevel);
	}

	@ResponseBody
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.level-rest-controller.delete.value}", notes = "${swagger.level-rest-controller.delete.notes}")
	public ResponseEntity<Level> delete(
			@ApiParam(value = "${swagger.level-rest-controller.delete}", required = true) @PathVariable("id") Long id) {

		final Level level = serviceLevel.getLevelById(id).get();
		try {
			if (level == null) {
				return ResponseEntity.notFound().build();
			}
			serviceLevel.Delete(level);
			;
			logEstablishmentInfo(level, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(level);
	}

	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.level-rest-controller.delete.value}", notes = "${swagger.level-rest-controller.delete.notes}")
	public ResponseEntity<Level> deleteById(
			@ApiParam(value = "${swagger.level-rest-controller.delete.id}", required = true) @PathVariable("id") Long id) {

		final Level level = serviceLevel.getLevelById(id).get();
		try {
			if (level == null) {
				return ResponseEntity.notFound().build();
			}
			serviceLevel.DeleteById(id);
			logEstablishmentInfo(level, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(level);
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.level-rest-controller.getById.value}", notes = "${swagger.level-rest-controller.getById.notes}")
	public ResponseEntity<Level> getLevelById(
			@ApiParam(value = "${swagger.level-rest-controller.getById.id}", required = true) @PathVariable("id") Long id) {
		final LevelDTO newLevelDTO;

		final Level level = serviceLevel.getLevelById(id).get();
		try {
			if (level == null) {
				return ResponseEntity.notFound().build();
			}
			newLevelDTO = modelMapper.map(level, LevelDTO.class);

			ResponseEntity.ok(newLevelDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(level);
	}

	@ResponseBody
	@GetMapping(value = "/{levelName}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.level-rest-controller.getLevelBylevelName.value}", notes = "${swagger.level-rest-controller.getLevelBylevelName.notes}")
	public ResponseEntity<List<Level>> getLevelBylevelName(
			@ApiParam(value = "${swagger.level-rest-controller.getLevelBylevelName.levelName}", required = true) @PathVariable("levelName") String levelName) {
		// final LevelDTO newLevelDTO;
		List<Level> levels = serviceLevel.getLevelBylevelName(levelName);

		try {
			if (levelName == null) {
				return ResponseEntity.notFound().build();
			}
			List<LevelDTO> levelDTO = levels.stream().map(level -> modelMapper.map(level, LevelDTO.class))
					.collect(Collectors.toList());

			ResponseEntity.ok(levelDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(levels);
	}

	private void logEstablishmentInfo(final Level level, final String message) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(Markers.append("app_level_id", level.getId())
					.and(Markers.append("app_level_levelName", level.getLevelName())), message);
		}
	}

}
