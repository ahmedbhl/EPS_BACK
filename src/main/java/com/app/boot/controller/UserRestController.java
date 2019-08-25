package com.app.boot.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.app.boot.dto.UserDTO;
import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Administration;
import com.app.boot.model.Professor;
import com.app.boot.model.Student;
import com.app.boot.model.User;
import com.app.boot.service.IServiceAdministration;
import com.app.boot.service.IServiceProfessor;
import com.app.boot.service.IServiceRole;
import com.app.boot.service.IServiceStudent;
import com.app.boot.service.IServiceUser;
import com.app.boot.utils.MailService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import net.logstash.logback.marker.Markers;

@CrossOrigin("*")
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
	IServiceAdministration administrationService;

	@Autowired
	IServiceStudent studentService;

	@Autowired
	IServiceProfessor professorService;

	@Autowired
	IServiceRole roleService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	MailService mailService;

	/**
	 * Adding pairing message
	 */

	private static final String ADDING_MESSAGE = "Adding new User";

	/**
	 * Delete pairing message
	 */
	private static final String DELETE_MESSAGE = "Delete User";

	/**
	 * Update pairing message
	 */
	private static final String UPDATE_MESSAGE = "Update User";

	/**
	 * Logger
	 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

	@ResponseBody
	@GetMapping(path = "/currentUser", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<UserDTO> user(Principal user) {
		return ResponseEntity.ok(
				modelMapper.map(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), UserDTO.class));
	}

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
			user.setDateOfRegistration(LocalDateTime.now());
			// Save the new user
			User createdUser = userService.createUser(user);

			// Map to DTO
			newUserDTO = modelMapper.map(createdUser, UserDTO.class);
			logUserInfo(createdUser, ADDING_MESSAGE);
			mailService.sendEmailRegistration(createdUser);
		} catch (CodeOperationException | MailException | MessagingException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newUserDTO);
	}

	/**
	 * Adding new Administration
	 * 
	 * @param userDTO
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "administration", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "${swagger.user-rest-controller.createAdministration.value}", notes = "${swagger.user-rest-controller.createAdministration.notes}")
	public ResponseEntity<UserDTO> createAdministration(
			@ApiParam(value = "${swagger.user-rest-controller.createAdministration.administration}", required = true) @Valid @RequestBody UserDTO AdministrationDTO) {
		// Map to model
		Administration administration = modelMapper.map(AdministrationDTO, Administration.class);

		final UserDTO newAdministrationDTO;
		try {
			administration.setRoles(Arrays.asList(roleService.getRoleByName("ADMINISTRATION")));
			administration.setDateOfRegistration(LocalDateTime.now());
			// Save the new Administration
			Administration createdAdministration = administrationService.createAdministration(administration);
			// Map to DTO
			newAdministrationDTO = modelMapper.map(createdAdministration, UserDTO.class);
			logUserInfo(createdAdministration, ADDING_MESSAGE);
			mailService.sendEmailRegistration(createdAdministration);

		} catch (CodeOperationException | MailException | MessagingException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newAdministrationDTO);
	}

	/**
	 * Adding new Student
	 * 
	 * @param userDTO
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "student", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "${swagger.user-rest-controller.createStudent.value}", notes = "${swagger.user-rest-controller.createStudent.notes}")
	public ResponseEntity<UserDTO> createStudent(
			@ApiParam(value = "${swagger.user-rest-controller.createStudent.Student}", required = true) @Valid @RequestBody UserDTO StudentDTO) {
		// Map to model
		Student student = modelMapper.map(StudentDTO, Student.class);
		final UserDTO newStudentDTO;
		try {
			student.setRoles(Arrays.asList(roleService.getRoleByName("STUDENT")));
			student.setDateOfRegistration(LocalDateTime.now());

			// Save the new Student
			Student createdStudent = studentService.createStudent(student);
			// Map to DTO
			newStudentDTO = modelMapper.map(createdStudent, UserDTO.class);
			logUserInfo(createdStudent, ADDING_MESSAGE);
			mailService.sendEmailRegistration(createdStudent);
		} catch (CodeOperationException | MailException | MessagingException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newStudentDTO);
	}

	/**
	 * Adding new Professor
	 * 
	 * @param userDTO
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "professor", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "${swagger.user-rest-controller.createProfessor.value}", notes = "${swagger.user-rest-controller.createProfessor.notes}")
	public ResponseEntity<UserDTO> createProfessor(
			@ApiParam(value = "${swagger.user-rest-controller.createProfessor.professor}", required = true) @Valid @RequestBody UserDTO ProfessorDTO) {

		// Map to model
		Professor professor = modelMapper.map(ProfessorDTO, Professor.class);
		final UserDTO newProfessorDTO;
		try {
			professor.setRoles(Arrays.asList(roleService.getRoleByName("PROFESSOR")));
			professor.setDateOfRegistration(LocalDateTime.now());
			// Save the new Professor
			Professor createdProfessor = professorService.createProfessor(professor);
			// Map to DTO
			newProfessorDTO = modelMapper.map(createdProfessor, UserDTO.class);
			logUserInfo(createdProfessor, ADDING_MESSAGE);
			mailService.sendEmailRegistration(createdProfessor);
		} catch (CodeOperationException | MailException | MessagingException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newProfessorDTO);
	}

	/**
	 * Update the User
	 * 
	 * @param userDTO
	 * @param id
	 * @return
	 */
	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.user-rest-controller.update.value}", notes = "${swagger.user-rest-controller.update.notes}")
	public ResponseEntity<UserDTO> updateUser(
			@ApiParam(value = "${swagger.user-rest-controller.update.user}") @RequestBody UserDTO userDTO,
			@ApiParam(value = "${swagger.user-rest-controller.update.user.id}") @PathVariable("id") Long id) {
		// Map with model
		User user = modelMapper.map(userDTO, User.class);
		final UserDTO updateUser;
		try {
			if (id != null && userService.getUserByid(id).isPresent()) {
				user.setId(id);
				user.setDateOfRegistration(LocalDateTime.now());
				// Update the user
				User updUser = userService.updateUser(user);
				// Map to dto
				updateUser = modelMapper.map(updUser, UserDTO.class);
				logUserInfo(updUser, UPDATE_MESSAGE);
				return ResponseEntity.status(HttpStatus.OK).body(updateUser);

			}
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Update the Administration
	 * 
	 * @param administrationDTO
	 * @param id
	 * @return
	 */
	@ResponseBody
	@PutMapping(value = "/administration/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.user-rest-controller.update.value}", notes = "${swagger.user-rest-controller.update.notes}")
	public ResponseEntity<UserDTO> updateAdministrateur(
			@ApiParam(value = "${swagger.user-rest-controller.update.administration}") @RequestBody UserDTO administrationDTO,
			@ApiParam(value = "${swagger.user-rest-controller.update.administration.id}") @PathVariable("id") Long id) {
		// Map with model
		Administration administration = modelMapper.map(administrationDTO, Administration.class);
		final UserDTO updateAdministration;
		try {
			if (id != null && administrationService.getAdministrationByid(id).isPresent()) {
				administration.setId(id);
				administration.setDateOfRegistration(LocalDateTime.now());
				// Update the administration
				Administration updAdministration = administrationService.updateAdministration(administration);
				// Map to dto
				updateAdministration = modelMapper.map(updAdministration, UserDTO.class);
				logUserInfo(updAdministration, UPDATE_MESSAGE);
				return ResponseEntity.status(HttpStatus.OK).body(updateAdministration);

			}
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Update the Professor
	 * 
	 * @param professorDTO
	 * @param id
	 * @return
	 */
	@ResponseBody
	@PutMapping(value = "/professor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.user-rest-controller.update.value}", notes = "${swagger.user-rest-controller.update.notes}")
	public ResponseEntity<UserDTO> updateProfessor(
			@ApiParam(value = "${swagger.user-rest-controller.update.professor}") @RequestBody UserDTO professorDTO,
			@ApiParam(value = "${swagger.user-rest-controller.update.professor.id}") @PathVariable("id") Long id) {
		// Map with model
		Professor professor = modelMapper.map(professorDTO, Professor.class);
		final UserDTO updateProfessor;
		try {
			if (id != null && professorService.getProfessorByid(id).isPresent()) {
				professor.setId(id);
				professor.setDateOfRegistration(LocalDateTime.now());
				// Update the professor
				Professor updProfessor = professorService.updateProfessor(professor);
				// Map to dto
				updateProfessor = modelMapper.map(updProfessor, UserDTO.class);
				logUserInfo(updProfessor, UPDATE_MESSAGE);
				return ResponseEntity.status(HttpStatus.OK).body(updateProfessor);

			}
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Update the Student
	 * 
	 * @param StudentDTO
	 * @param id
	 * @return
	 */
	@ResponseBody
	@PutMapping(value = "/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.user-rest-controller.update.value}", notes = "${swagger.user-rest-controller.update.notes}")
	public ResponseEntity<UserDTO> updateStudent(
			@ApiParam(value = "${swagger.user-rest-controller.update.student}") @RequestBody UserDTO studentDTO,
			@ApiParam(value = "${swagger.user-rest-controller.update.student.id}") @PathVariable("id") Long id) {
		// Map with model
		Student student = modelMapper.map(studentDTO, Student.class);
		final UserDTO updateStudent;
		try {
			if (id != null && studentService.getStudentByid(id).isPresent()) {
				student.setId(id);
				student.setDateOfRegistration(LocalDateTime.now());
				// Update the student
				Student updStudent = studentService.updateStudent(student);
				// Map to dto
				updateStudent = modelMapper.map(updStudent, UserDTO.class);
				logUserInfo(updStudent, UPDATE_MESSAGE);
				return ResponseEntity.status(HttpStatus.OK).body(updateStudent);

			}
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Delete the User having the passed id.
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.user-rest-controller.delete.value}", notes = "${swagger.user-rest-controller.delete.notes}")
	public ResponseEntity<User> deleteUser(
			@ApiParam(value = "${swagger.user-rest-controller.delete.id}", required = true) @PathVariable("id") Long id) {
		final User user;
		try {
			user = userService.deleteUser(id);
			if (user == null) {
				return ResponseEntity.notFound().build();
			}
			logUserInfo(user, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(user);
	}

	/**
	 * Update the User
	 * 
	 * @param userDTO
	 * @param id
	 * @return
	 */
	@ResponseBody
	@PutMapping(value = "/activate/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.user-rest-controller.activateUser.value}", notes = "${swagger.user-rest-controller.update.notes}")
	public ResponseEntity<Boolean> activateUser(
			@ApiParam(value = "${swagger.user-rest-controller.activateUser.user.key}") @PathVariable("key") String key) {
		try {
			if (key != null) {
				Optional<User> user = userService.getUserByid(Long.parseLong(key.substring(13)));
				if (user.isPresent()) {
					user.get().setEnabled(true);
					// Update the user
					User updUser = userService.updateUser(user.get());
					logUserInfo(updUser, "User Activated With Success User");
					return ResponseEntity.status(HttpStatus.OK).body(true);
				}
			}
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * 
	 * @param pairingModel
	 * @param message
	 */
	private void logUserInfo(final User user, final String message) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(
					Markers.append("app_user_id", user.getId()).and(Markers.append("app_user_email", user.getEmail()))
							.and(Markers.append("app_user_familyName", user.getLastName()))
							.and(Markers.append("app_user_firstName", user.getFirstName()))
							.and(Markers.append("app_user_creationDate", user.getDateOfRegistration())),
					message);
		}
	}

}
