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

import com.app.boot.dto.GroupDTO;
import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Group;
import com.app.boot.service.IServiceGroup;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import net.logstash.logback.marker.Markers;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/groups")
@SwaggerDefinition(tags = {
		@Tag(name = "GroupRestController", description = "${swagger.group-rest-controller.description}") })
@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 408, message = "Request Time-out"),
		@ApiResponse(code = 500, message = "Internal Server Error	") })
public class GroupRestController {

	@Autowired
	private IServiceGroup serviceGroup;

	@Autowired
	private ModelMapper modelMapper;

	private static final String ADDING_MESSAGE = " Group Adding ";

	/**
	 * Delete pairing message
	 */
	private static final String DELETE_MESSAGE = "Delete Group";

	/**
	 * Update pairing message
	 */
	private static final String UPDATE_MESSAGE = "Update Group";

	/**
	 * Logger
	 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(GroupRestController.class);

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.group-rest-controller.getAllGroups.value}", notes = "${swagger.group-rest-controller.getAllGroups.notes}")
	public ResponseEntity<List<GroupDTO>> getAll() {
		List<Group> groups = serviceGroup.getAll();
		List<GroupDTO> groupDTO = groups.stream().map(group -> modelMapper.map(group, GroupDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(groupDTO);
	}

	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "${swagger.group-rest-controller.createGroup.value}", notes = "${swagger.group-rest-controller.createGroup.notes}")
	public ResponseEntity<GroupDTO> createGroup(
			@ApiParam(value = "${swagger.group-rest-controller.createGroup.group}", required = true) @Valid @RequestBody GroupDTO groupDTO) {
		// Map to model
		Group group = modelMapper.map(groupDTO, Group.class);
		final GroupDTO newgroupDTO;
		try {
			// Save the new Group
			Group createdGroup = serviceGroup.Create(group);
			// Map to DTO
			newgroupDTO = modelMapper.map(createdGroup, GroupDTO.class);
			logGroupInfo(createdGroup, ADDING_MESSAGE);
		} catch (CodeOperationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newgroupDTO);
	}

	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.group-rest-controller.update.value}", notes = "${swagger.group-rest-controller.update.notes}")
	public ResponseEntity<GroupDTO> updateGroup(
			@ApiParam(value = "${swagger.group-rest-controller.update.group}") @RequestBody GroupDTO groupDTO,
			@ApiParam(value = "${swagger.group-rest-controller.update.group.id}") @PathVariable("id") Long id) {
		// Map with model
		Group group = modelMapper.map(groupDTO, Group.class);
		final GroupDTO updateGroup;
		try {

			// Update the group
			Group updatedGroup = serviceGroup.Update(group);
			// Map to dto
			updateGroup = modelMapper.map(updatedGroup, GroupDTO.class);
			logGroupInfo(updatedGroup, UPDATE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updateGroup);
	}

	@ResponseBody
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.group-rest-controller.delete.value}", notes = "${swagger.group-rest-controller.delete.notes}")
	public ResponseEntity<Group> delete(
			@ApiParam(value = "${swagger.group-rest-controller.delete}", required = true) @PathVariable("id") Long id) {

		final Group group = serviceGroup.getGroupById(id).get();
		try {
			if (group == null) {
				return ResponseEntity.notFound().build();
			}
			serviceGroup.Delete(group);
			;
			logGroupInfo(group, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(group);
	}

	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.group-rest-controller.deleteById.value}", notes = "${swagger.group-rest-controller.deleteById.notes}")
	public ResponseEntity<Group> deleteById(
			@ApiParam(value = "${swagger.group-rest-controller.deleteById.id}", required = true) @PathVariable("id") Long id) {

		final Group group = serviceGroup.getGroupById(id).get();
		try {
			if (group == null) {
				return ResponseEntity.notFound().build();
			}
			serviceGroup.DeleteById(id);
			logGroupInfo(group, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(group);
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.group-rest-controller.getById.value}", notes = "${swagger.group-rest-controller.getById.notes}")
	public ResponseEntity<Group> getGroupById(
			@ApiParam(value = "${swagger.group-rest-controller.getById.id}", required = true) @PathVariable("id") Long id) {
		final GroupDTO newGroupDTO;

		final Group group = serviceGroup.getGroupById(id).get();
		try {
			if (group == null) {
				return ResponseEntity.notFound().build();
			}
			newGroupDTO = modelMapper.map(group, GroupDTO.class);

			ResponseEntity.ok(newGroupDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(group);
	}

	@ResponseBody
	@GetMapping(value = "/{groupName}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.group-rest-controller.getGroupBygroupName.value}", notes = "${swagger.group-rest-controller.getGroupBygroupName.notes}")
	public ResponseEntity<List<Group>> getGroupBygroupName(
			@ApiParam(value = "${swagger.group-rest-controller.getGroupBygroupName.groupName}", required = true) @PathVariable("groupName") String groupName) {
		// final GroupDTO newGroupDTO;
		List<Group> groups = serviceGroup.getGroupBygroupName(groupName);

		try {
			if (groupName == null) {
				return ResponseEntity.notFound().build();
			}
			List<GroupDTO> groupDTO = groups.stream().map(group -> modelMapper.map(group, GroupDTO.class))
					.collect(Collectors.toList());

			ResponseEntity.ok(groupDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(groups);
	}

	private void logGroupInfo(final Group group, final String message) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(Markers.append("app_group_id", group.getId())
					.and(Markers.append("app_group_groupName", group.getGroupName()))
					.and(Markers.append("app_group_hashCode", group.getHashCode())), message);
		}
	}

}
