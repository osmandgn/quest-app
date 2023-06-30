package com.questapp.repository;

import com.questapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUserIdAndPostId();

    List<Comment> findAllByUserId();

    List<Comment> findAllByPostId();
}
