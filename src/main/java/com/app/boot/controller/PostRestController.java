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

import com.app.boot.dto.PostDTO;
import com.app.boot.exception.CodeOperationException;
import com.app.boot.model.Post;
import com.app.boot.service.IServicePost;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import net.logstash.logback.marker.Markers;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/posts")
@SwaggerDefinition(tags = {
		@Tag(name = "PostRestController", description = "${swagger.post-rest-controller.description}") })
@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 408, message = "Request Time-out"),
		@ApiResponse(code = 500, message = "Internal Server Error	") })
public class PostRestController {
	@Autowired
	private IServicePost servicePost;

	@Autowired
	private ModelMapper modelMapper;

	private static final String ADDING_MESSAGE = " Post Adding ";

	/**
	 * Delete pairing message
	 */
	private static final String DELETE_MESSAGE = "Delete Post";

	/**
	 * Update pairing message
	 */
	private static final String UPDATE_MESSAGE = "Update Post";

	/**
	 * Logger
	 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(PostRestController.class);

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.post-rest-controller.getAllPosts.value}", notes = "${swagger.post-rest-controller.getAllPosts.notes}")
	public ResponseEntity<List<PostDTO>> getAll() {
		List<Post> posts = servicePost.getAll();
		List<PostDTO> postDTO = posts.stream().map(post -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(postDTO);
	}

	@ResponseBody
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "${swagger.post-rest-controller.createPost.value}", notes = "${swagger.post-rest-controller.createPost.notes}")
	public ResponseEntity<PostDTO> createPost(
			@ApiParam(value = "${swagger.post-rest-controller.createPost.post}", required = true) @Valid @RequestBody PostDTO postDTO) {
		// Map to model
		Post post = modelMapper.map(postDTO, Post.class);
		final PostDTO newpostDTO;
		try {
			// Save the new Post
			Post createdPost = servicePost.create(post);
			// Map to DTO
			newpostDTO = modelMapper.map(createdPost, PostDTO.class);
			logPostInfo(createdPost, ADDING_MESSAGE);
		} catch (CodeOperationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.ok(newpostDTO);
	}

	@ResponseBody
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.post-rest-controller.update.value}", notes = "${swagger.post-rest-controller.update.notes}")
	public ResponseEntity<PostDTO> updatePost(
			@ApiParam(value = "${swagger.post-rest-controller.update.post}") @RequestBody PostDTO postDTO,
			@ApiParam(value = "${swagger.post-rest-controller.update.post.id}") @PathVariable("id") Long id) {
		// Map with model
		Post post = modelMapper.map(postDTO, Post.class);
		final PostDTO updatePost;
		try {

			// Update the post
			Post updatedPost = servicePost.update(post);
			// Map to dto
			updatePost = modelMapper.map(updatedPost, PostDTO.class);
			logPostInfo(updatedPost, UPDATE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(updatePost);
	}

	@ResponseBody
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.post-rest-controller.delete.value}", notes = "${swagger.post-rest-controller.delete.notes}")
	public ResponseEntity<Post> delete(
			@ApiParam(value = "${swagger.post-rest-controller.delete}", required = true) @PathVariable("id") Long id) {

		final Post post = servicePost.getPostById(id).get();
		try {
			if (post == null) {
				return ResponseEntity.notFound().build();
			}
			servicePost.delete(post);
			;
			logPostInfo(post, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(post);
	}

	@ResponseBody
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.post-rest-controller.deleteById.value}", notes = "${swagger.post-rest-controller.deleteById.notes}")
	public ResponseEntity<Post> deleteById(
			@ApiParam(value = "${swagger.post-rest-controller.deleteById.id}", required = true) @PathVariable("id") Long id) {

		final Post post = servicePost.getPostById(id).get();
		try {
			if (post == null) {
				return ResponseEntity.notFound().build();
			}
			servicePost.deleteById(id);
			logPostInfo(post, DELETE_MESSAGE);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(post);
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "${swagger.post-rest-controller.getById.value}", notes = "${swagger.post-rest-controller.getById.notes}")
	public ResponseEntity<Post> getPostById(
			@ApiParam(value = "${swagger.post-rest-controller.getById.id}", required = true) @PathVariable("id") Long id) {
		final PostDTO newPostDTO;

		final Post post = servicePost.getPostById(id).get();
		try {
			if (post == null) {
				return ResponseEntity.notFound().build();
			}
			newPostDTO = modelMapper.map(post, PostDTO.class);

			ResponseEntity.ok(newPostDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(post);
	}

	private void logPostInfo(final Post post, final String message) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(Markers.append("app_post_id", post.getId())
					.and(Markers.append("app_post_type", post.getType())), message);
		}
	}

}
