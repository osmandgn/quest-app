package com.questapp.service;

import com.questapp.model.Comment;
import com.questapp.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;

    public CommentService(PostService postService, UserService userService, CommentRepository commentRepository) {
        this.postService = postService;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }


    public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return commentRepository.findAllByUserIdAndPostId();
        } else if (userId.isPresent()) {
            return commentRepository.findAllByUserId();
        } else if (postId.isPresent()) {
            return commentRepository.findAllByPostId();
        }else return commentRepository.findAll();
    }


    public Comment getCommentById(Long id) {
       return commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
    }
}
