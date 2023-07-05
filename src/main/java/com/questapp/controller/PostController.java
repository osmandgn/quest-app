package com.questapp.controller;

import com.questapp.dto.request.PostCreateRequest;
import com.questapp.dto.request.PostUpdateRequest;
import com.questapp.dto.response.PostResponseDTO;
import com.questapp.model.Post;
import com.questapp.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(@RequestParam("userId") Optional<Long> userId){
        List<PostResponseDTO> posts = postService.getAllPosts(userId);
        return ResponseEntity.ok(posts);
    }


    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getPostDTOById(postId));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createNewPost(@RequestBody PostCreateRequest postCreateRequest){
        postService.createPost(postCreateRequest);
        Map<String, String> map = new HashMap<>();
        map.put("status", "true");
        map.put("message", "Post created successfully");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
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
