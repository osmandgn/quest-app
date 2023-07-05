package com.questapp.service;

import com.questapp.dto.request.CommentCreateRequest;
import com.questapp.dto.request.CommentUpdateRequest;
import com.questapp.model.Comment;
import com.questapp.model.Post;
import com.questapp.model.User;
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
            return commentRepository.findAllByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findAllByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findAllByPostId(postId.get());
        }else return commentRepository.findAll();
    }


    public Comment getCommentById(Long id) {
       return commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public Comment createComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getUser(commentCreateRequest.getUserId());
        Post post = postService.getPostById(commentCreateRequest.getPostId());
        Comment comment = new Comment();
        comment.setText(commentCreateRequest.getText());
        comment.setUser(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public void updateComment(Long id, CommentUpdateRequest commentUpdateRequest) {
        Comment comment = getCommentById(id);
        comment.setText(commentUpdateRequest.getText());
    }

    public void deleteComment(Long id) {
        Comment comment = getCommentById(id);
        commentRepository.delete(comment);
    }
}
