package com.questapp.controller;

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
    public ResponseEntity<String> createComment(@RequestBody CommentCreateRequest commentCreateRequest){

    }

    @PutMapping
    public ResponseEntity<String> updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequest commentUpdateRequest){

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id){

    }

}
