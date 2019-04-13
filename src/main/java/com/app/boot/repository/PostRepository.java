package com.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.boot.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
