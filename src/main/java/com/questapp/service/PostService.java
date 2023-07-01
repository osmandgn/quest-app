package com.questapp.service;

import com.questapp.dto.request.PostCreateRequest;
import com.questapp.dto.request.PostUpdateRequest;
import com.questapp.dto.response.PostResponseDTO;
import com.questapp.model.Post;
import com.questapp.model.User;
import com.questapp.repository.PostRepository;
import com.questapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserRepository userRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }



    public List<PostResponseDTO> getAllPosts(Optional<Long> userId) {
        List<Post> postList = new ArrayList<>();
        if (userId.isPresent()){
           postList = postRepository.findByUserId(userId);
        }else {
            postList = postRepository.findAll();
        }
        return postList.stream().map((post) -> new PostResponseDTO(post)).collect(Collectors.toList());
    }



    public Post getPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post Not Found"));
    }


    public void createPost(PostCreateRequest postCreateRequest) {
        User user = userService.getUser(postCreateRequest.getUserId());
        Post post = new Post();
        post.setId(postCreateRequest.getId());
        post.setTitle(postCreateRequest.getTitle());
        post.setText(postCreateRequest.getText());
        post.setUser(user);
        postRepository.save(post);
    }

    public void updatePost(Long id, PostUpdateRequest postUpdateRequest) {
        Post post = getPostById(id);
        post.setTitle(postUpdateRequest.getTitle());
        post.setText(postUpdateRequest.getText());
    }

    public void deletePost(Long id) {
        Post post = getPostById(id);
        postRepository.delete(post);
    }


}
