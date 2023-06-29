package com.questapp.controller;

import com.questapp.model.Post;
import com.questapp.service.PostService;
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
        return ResponseEntity.ok(post)
    }
}
