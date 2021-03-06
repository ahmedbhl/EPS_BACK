package com.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.boot.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
