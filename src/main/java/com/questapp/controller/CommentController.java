package com.questapp.controller;

import com.questapp.dto.request.CommentCreateRequest;
import com.questapp.dto.request.CommentUpdateRequest;
import com.questapp.model.Comment;
import com.questapp.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(@RequestParam("userId") Optional<Long> userId,
                                        @RequestParam("postId") Optional<Long> postId){
        List<Comment> commentList = commentService.getAllComments(userId, postId);
        return ResponseEntity.ok(commentList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id){
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentCreateRequest commentCreateRequest){
       Comment comment =commentService.createComment(commentCreateRequest);
        return ResponseEntity.ok(comment);
    }

    @PutMapping
    public ResponseEntity<String> updateComment(@PathVariable Long id,
                                                @RequestBody CommentUpdateRequest commentUpdateRequest){
        commentService.updateComment(id, commentUpdateRequest);
        String message = "Comment updated successfully";
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        String message = "Comment deleted successfully";
        return ResponseEntity.ok(message);
    }

}
