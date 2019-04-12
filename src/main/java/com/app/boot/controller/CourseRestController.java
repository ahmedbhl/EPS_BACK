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

import com.app.boot.dto.CourseDTO;
import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Course;
import com.app.boot.service.IServiceCourse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import net.logstash.logback.marker.Markers;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/courses")
@SwaggerDefinition(tags = {
		@Tag(name = "CourseRestController", description = "${swagger.course-rest-controller.description}") })
@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 408, message = "Request Time-out"),
		@ApiResponse(code = 500, message = "Internal Server Error	") })
public class CourseRestController {

	@Autowired
	private IServiceCourse serviceCourse;

	@Autowired
	private ModelMapper modelMapper;

	private static final String ADDING_MESSAGE = " Course Adding ";

	/**
	 * Delete pairing message
	 */
	private static final String DELETE_MESSAGE = "Delete Course";

	/**
	 * Update pairing message
	 */
	private static final String UPDATE_MESSAGE = "Update Course";

	/**
	 * Logger
	 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseRestController.class);

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.course-rest-controller.getAllCourses.value}", notes = "${swagger.course-rest-controller.getAllCourses.notes}")
	public ResponseEntity<List<CourseDTO>> getAll() {
		List<Course> courses = serviceCourse.getAll();
		List<CourseDTO> courseDTO = courses.stream().map(course -> modelMapper.map(course, CourseDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(courseDTO);
	}

	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "${swagger.course-rest-controller.createCourse.value}", notes = "${swagger.course-rest-controller.createCourse.notes}")
	public ResponseEntity<CourseDTO> createCourse(
			@ApiParam(value = "${swagger.course-rest-controller.createCourse.course}", required = true) @Valid @RequestBody CourseDTO courseDTO) {
		// Map to model
		Course course = modelMapper.map(courseDTO, Course.class);
		final CourseDTO newcourseDTO;
		try {
			// Save the new Course
			Course createdCourse = serviceCourse.Create(course);
			// Map to DTO
			newcourseDTO = modelMapper.map(createdCourse, CourseDTO.class);
			logCourseInfo(createdCourse, ADDING_MESSAGE);
		} catch (CodeOperationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newcourseDTO);
	}

	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.course-rest-controller.update.value}", notes = "${swagger.course-rest-controller.update.notes}")
	public ResponseEntity<CourseDTO> updateCourse(
			@ApiParam(value = "${swagger.course-rest-controller.update.course}") @RequestBody CourseDTO courseDTO,
			@ApiParam(value = "${swagger.course-rest-controller.update.course.id}") @PathVariable("id") Long id) {
		// Map with model
		Course course = modelMapper.map(courseDTO, Course.class);
		final CourseDTO updateCourse;
		try {

			// Update the course
			Course updatedCourse = serviceCourse.Update(course);
			// Map to dto
			updateCourse = modelMapper.map(updatedCourse, CourseDTO.class);
			logCourseInfo(updatedCourse, UPDATE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updateCourse);
	}

	@ResponseBody
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.course-rest-controller.delete.value}", notes = "${swagger.course-rest-controller.delete.notes}")
	public ResponseEntity<Course> delete(
			@ApiParam(value = "${swagger.course-rest-controller.delete}", required = true) @PathVariable("id") Long id) {

		final Course course = serviceCourse.getCourseById(id).get();
		try {
			if (course == null) {
				return ResponseEntity.notFound().build();
			}
			serviceCourse.Delete(course);
			;
			logCourseInfo(course, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(course);
	}

	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.course-rest-controller.deleteById.value}", notes = "${swagger.course-rest-controller.deleteById.notes}")
	public ResponseEntity<Course> deleteById(
			@ApiParam(value = "${swagger.course-rest-controller.deleteById.id}", required = true) @PathVariable("id") Long id) {

		final Course course = serviceCourse.getCourseById(id).get();
		try {
			if (course == null) {
				return ResponseEntity.notFound().build();
			}
			serviceCourse.DeleteById(id);
			logCourseInfo(course, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(course);
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.course-rest-controller.getById.value}", notes = "${swagger.course-rest-controller.getById.notes}")
	public ResponseEntity<Course> getCourseById(
			@ApiParam(value = "${swagger.course-rest-controller.getById.id}", required = true) @PathVariable("id") Long id) {
		final CourseDTO newCourseDTO;

		final Course course = serviceCourse.getCourseById(id).get();
		try {
			if (course == null) {
				return ResponseEntity.notFound().build();
			}
			newCourseDTO = modelMapper.map(course, CourseDTO.class);

			ResponseEntity.ok(newCourseDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(course);
	}

	@ResponseBody
	@GetMapping(value = "/{courseName}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.course-rest-controller.getCourseBycourseName.value}", notes = "${swagger.course-rest-controller.getCourseBycourseName.notes}")
	public ResponseEntity<List<Course>> getCourseBycourseName(
			@ApiParam(value = "${swagger.course-rest-controller.getCourseBycourseName.courseName}", required = true) @PathVariable("courseName") String courseName) {
		// final CourseDTO newCourseDTO;
		List<Course> courses = serviceCourse.getCourseBycourseName(courseName);

		try {
			if (courseName == null) {
				return ResponseEntity.notFound().build();
			}
			List<CourseDTO> courseDTO = courses.stream().map(course -> modelMapper.map(course, CourseDTO.class))
					.collect(Collectors.toList());

			ResponseEntity.ok(courseDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(courses);
	}

	private void logCourseInfo(final Course course, final String message) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(Markers.append("app_course_id", course.getId())
					.and(Markers.append("app_course_courseName", course.getCourseName())), message);
		}
	}

}
