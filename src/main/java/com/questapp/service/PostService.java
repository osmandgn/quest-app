package com.questapp.service;

import com.questapp.dto.request.PostCreateRequest;
import com.questapp.model.Post;
import com.questapp.model.User;
import com.questapp.repository.PostRepository;
import com.questapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserRepository userRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
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


    public void createPost(PostCreateRequest postCreateRequest) {
        User user = userService.getUser(postCreateRequest.getUserId());
        Post post = new Post();
        post.setId(postCreateRequest.getId());
        post.setTitle(postCreateRequest.getTitle());
        post.setText(postCreateRequest.getText());
        post.setUser(user);
    }
}
