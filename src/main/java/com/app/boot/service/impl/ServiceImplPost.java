package com.app.boot.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.boot.model.Post;
import com.app.boot.repository.PostRepository;
import com.app.boot.service.IServicePost;

@Service
@Transactional
public class ServiceImplPost implements IServicePost {
	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> getAll() {
		return postRepository.findAll();
	}

	@Override
	public Post create(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post update(Post post) {
		return postRepository.saveAndFlush(post);
	}

	@Override
	public void delete(Post post) {
		postRepository.delete(post);
		;
	}

	@Override
	public void deleteById(Long id) {
		postRepository.deleteById(id);
	}

	@Override
	public Optional<Post> getPostById(Long id) {
		return postRepository.findById(id);
	}

}
