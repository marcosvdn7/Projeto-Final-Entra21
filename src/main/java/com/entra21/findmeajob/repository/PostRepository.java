package com.entra21.findmeajob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entra21.findmeajob.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	Optional<Post> findById(Long id);
	
}
