package com.questapp.service;

import com.questapp.model.Post;
import com.questapp.repository.PostRepository;

import java.util.List;
import java.util.Optional;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts(Optional<Long> userId) {
        if (userId.isPresent()){
           return postRepository.findByUserId(userId);
        }
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post Not Found"));
    }
}
