package com.questapp.controller;

import com.questapp.dto.request.PostCreateRequest;
import com.questapp.dto.request.PostUpdateRequest;
import com.questapp.model.Post;
import com.questapp.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam("userId") Optional<Long> userId){
        List<Post> posts = postService.getPosts(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId){
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<String> createNewPost(@RequestBody PostCreateRequest postCreateRequest){
        postService.createPost(postCreateRequest);
        String message = "Post created successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id,
                                             @RequestBody PostUpdateRequest postUpdateRequest){
        postService.updatePost(id, postUpdateRequest);
        String message = "Post updated successfully";
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        String message = "Post deleted successfully";
        return ResponseEntity.ok(message);
    }


}
