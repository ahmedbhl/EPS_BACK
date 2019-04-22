package com.app.boot.controller;

import java.util.Date;
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

import com.app.boot.dto.EstablishmentDTO;
import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Establishment;
import com.app.boot.service.IServiceEstablishment;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import net.logstash.logback.marker.Markers;

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

	/**
	 * Logger
	 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(EstablishmentRestController.class);

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.establishment-rest-controller.getAllEstablishments.value}", notes = "${swagger.establishment-rest-controller.getAllEstablishments.notes}")
	public ResponseEntity<List<EstablishmentDTO>> getAllEstablishments() {
		List<Establishment> establishments = serviceEstablishment.getAll();
		List<EstablishmentDTO> establishmentsDTO = establishments.stream()
				.map(establishment -> modelMapper.map(establishment, EstablishmentDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(establishmentsDTO);
	}

	/**
	 * 
	 * @param establishmentDTO
	 * @return
	 */
	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "${swagger.establishment-rest-controller.createEstablishment.value}", notes = "${swagger.establishment-rest-controller.createEstablishment.notes}")
	public ResponseEntity<EstablishmentDTO> createEstablishment(
			@ApiParam(value = "${swagger.establishment-rest-controller.createEstablishment.establishment}", required = true) @Valid @RequestBody EstablishmentDTO establishmentDTO) {
		// Map to model
		Establishment establishment = modelMapper.map(establishmentDTO, Establishment.class);
		final EstablishmentDTO newEstablishmentDTO;
		try {
			// Save the new Establishment
			Establishment createdEstablishment = serviceEstablishment.Create(establishment);
			// Map to DTO
			newEstablishmentDTO = modelMapper.map(createdEstablishment, EstablishmentDTO.class);
			logEstablishmentInfo(createdEstablishment, ADDING_MESSAGE);
		} catch (CodeOperationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newEstablishmentDTO);
	}

	/**
	 * Update the Establishment
	 * 
	 * @param EstablishmentDTO
	 * @param id
	 * @return
	 */
	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.establishment-rest-controller.update.value}", notes = "${swagger.establishment-rest-controller.update.notes}")
	public ResponseEntity<EstablishmentDTO> updateEstablishment(
			@ApiParam(value = "${swagger.establishment-rest-controller.update.establishment}") @RequestBody EstablishmentDTO establishmentDTO,
			@ApiParam(value = "${swagger.establishment-rest-controller.update.establishment.id}") @PathVariable("id") Long id) {
		// Map with model
		Establishment establishment = modelMapper.map(establishmentDTO, Establishment.class);
		final EstablishmentDTO updateEstablishment;
		try {
			establishment.setYearOfFoundation(new Date());
			// Update the establishment
			Establishment updatedEstablishment = serviceEstablishment.Update(establishment);
			// Map to dto
			updateEstablishment = modelMapper.map(updatedEstablishment, EstablishmentDTO.class);
			logEstablishmentInfo(updatedEstablishment, UPDATE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updateEstablishment);
	}

	@ResponseBody
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.establishment-rest-controller.delete.value}", notes = "${swagger.establishment-rest-controller.delete.notes}")
	public ResponseEntity<Establishment> delete(
			@ApiParam(value = "${swagger.establishment-rest-controller.delete}", required = true) @PathVariable("id") Long id) {

		final Establishment establishment = serviceEstablishment.getEstablishmentById(id).get();
		try {
			if (establishment == null) {
				return ResponseEntity.notFound().build();
			}
			serviceEstablishment.Delete(establishment);
			;
			logEstablishmentInfo(establishment, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(establishment);
	}

	/**
	 * Delete the Establishment having the passed id.
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.establishment-rest-controller.delete.value}", notes = "${swagger.establishment-rest-controller.delete.notes}")
	public ResponseEntity<Establishment> deleteById(
			@ApiParam(value = "${swagger.establishment-rest-controller.delete.id}", required = true) @PathVariable("id") Long id) {

		final Establishment establishment = serviceEstablishment.getEstablishmentById(id).get();
		try {
			if (establishment == null) {
				return ResponseEntity.notFound().build();
			}
			serviceEstablishment.DeleteById(id);
			logEstablishmentInfo(establishment, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(establishment);
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.establishment-rest-controller.getById.value}", notes = "${swagger.establishment-rest-controller.getById.notes}")
	public ResponseEntity<Establishment> getEstablishmentById(
			@ApiParam(value = "${swagger.establishment-rest-controller.getById.id}", required = true) @PathVariable("id") Long id) {
		final EstablishmentDTO newEstablishmentDTO;

		final Establishment establishment = serviceEstablishment.getEstablishmentById(id).get();
		try {
			if (establishment == null) {
				return ResponseEntity.notFound().build();
			}
			newEstablishmentDTO = modelMapper.map(establishment, EstablishmentDTO.class);

			ResponseEntity.ok(newEstablishmentDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(establishment);
	}

	@ResponseBody
	@GetMapping(value = "/{establishmentName}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.establishment-rest-controller.getEstablishmentByestablishmentName.value}", notes = "${swagger.establishment-rest-controller.getEstablishmentByestablishmentName.notes}")
	public ResponseEntity<List<Establishment>> getEstablishmentByestablishmentName(
			@ApiParam(value = "${swagger.establishment-rest-controller.getEstablishmentByestablishmentName.establishmentName}", required = true) @PathVariable("establishmentName") String establishmentName) {
		// final EstablishmentDTO newEstablishmentDTO;
		List<Establishment> establishments = serviceEstablishment
				.getEstablishmentByestablishmentName(establishmentName);

		try {
			if (establishmentName == null) {
				return ResponseEntity.notFound().build();
			}
			List<EstablishmentDTO> establishmentDTO = establishments.stream()
					.map(establishment -> modelMapper.map(establishment, EstablishmentDTO.class))
					.collect(Collectors.toList());

			ResponseEntity.ok(establishmentDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(establishments);
	}

	/**
	 * 
	 * @param pairingModel
	 * @param message
	 */
	private void logEstablishmentInfo(final Establishment establishment, final String message) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(Markers.append("app_establishment_id", establishment.getId())
					.and(Markers.append("app_establishment_establishmentName", establishment.getEstablishmentName()))
					.and(Markers.append("app_establishment_yearOfFoundation", establishment.getYearOfFoundation()))
					.and(Markers.append("app_establishment_location", establishment.getLocation())), message);
		}
	}

}
