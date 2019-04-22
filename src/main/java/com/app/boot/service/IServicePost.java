package com.app.boot.service;

import java.util.List;
import java.util.Optional;

import com.app.boot.model.Post;

public interface IServicePost {

	public List<Post> getAll();

	public Post create(Post post);

	public Post update(Post post);

	public void delete(Post post);

	public void deleteById(Long id);

	public Optional<Post> getPostById(Long id);

}
