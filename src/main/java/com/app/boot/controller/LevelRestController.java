package com.app.boot.controller;

import java.util.List;
import java.util.Optional;
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

@CrossOrigin("*")
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

	private static final String ADDING_MESSAGE = " Level Adding ";

	/**
	 * Delete pairing message
	 */
	private static final String DELETE_MESSAGE = "Delete Level";

	/**
	 * Update pairing message
	 */
	private static final String UPDATE_MESSAGE = "Update Level";

	/**
	 * Logger
	 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(LevelRestController.class);

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
			// Save the new Level
			Level createdLevel = serviceLevel.create(level);
			// Map to DTO
			newlevelDTO = modelMapper.map(createdLevel, LevelDTO.class);
			logLevelInfo(createdLevel, ADDING_MESSAGE);
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
			Level updatedLevel = serviceLevel.update(level);
			// Map to dto
			updateLevel = modelMapper.map(updatedLevel, LevelDTO.class);
			logLevelInfo(updatedLevel, UPDATE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updateLevel);
	}

	@ResponseBody
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.level-rest-controller.delete.value}", notes = "${swagger.level-rest-controller.delete.notes}")
	public ResponseEntity<LevelDTO> delete(
			@ApiParam(value = "${swagger.level-rest-controller.delete}", required = true) @RequestBody LevelDTO levelDTO) {

		final LevelDTO deletedLevelDTO;
		try {
			Level level = modelMapper.map(levelDTO, Level.class);
			level = serviceLevel.delete(level);
			if (level == null) {
				return ResponseEntity.notFound().build();
			}
			deletedLevelDTO = modelMapper.map(level, LevelDTO.class);

			logLevelInfo(level, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(deletedLevelDTO);
	}

	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.level-rest-controller.deleteById.value}", notes = "${swagger.level-rest-controller.deleteById.notes}")
	public ResponseEntity<LevelDTO> deleteById(
			@ApiParam(value = "${swagger.level-rest-controller.deleteById.id}", required = true) @PathVariable("id") Long id) {

		final LevelDTO levelDTO;
		try {
			final Level level = serviceLevel.deleteById(id);
			if (level == null) {
				return ResponseEntity.notFound().build();
			}
			levelDTO = modelMapper.map(level, LevelDTO.class);

			logLevelInfo(level, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(levelDTO);
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.level-rest-controller.getById.value}", notes = "${swagger.level-rest-controller.getById.notes}")
	public ResponseEntity<LevelDTO> getLevelById(
			@ApiParam(value = "${swagger.level-rest-controller.getById.id}", required = true) @PathVariable("id") Long id) {
		final LevelDTO newLevelDTO;

		final Optional<Level> level = serviceLevel.getLevelById(id);
		try {
			if (!level.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			newLevelDTO = modelMapper.map(level.get(), LevelDTO.class);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(newLevelDTO);
	}

	@ResponseBody
	@GetMapping(value = "/name/{levelName}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.level-rest-controller.getLevelBylevelName.value}", notes = "${swagger.level-rest-controller.getLevelBylevelName.notes}")
	public ResponseEntity<List<LevelDTO>> getLevelBylevelName(
			@ApiParam(value = "${swagger.level-rest-controller.getLevelBylevelName.levelName}", required = true) @PathVariable("levelName") String levelName) {

		List<Level> levels = serviceLevel.getLevelBylevelName(levelName);
		final List<LevelDTO> levelDTO;
		try {
			if (levelName == null) {
				return ResponseEntity.notFound().build();
			}
			levelDTO = levels.stream().map(level -> modelMapper.map(level, LevelDTO.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(levelDTO);
	}

	private void logLevelInfo(final Level level, final String message) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(Markers.append("app_level_id", level.getId())
					.and(Markers.append("app_level_levelName", level.getLevelName())), message);
		}
	}

}
