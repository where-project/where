package com.where.where.repository;

import com.where.where.model.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> getByPlaceId(Long id);
}
