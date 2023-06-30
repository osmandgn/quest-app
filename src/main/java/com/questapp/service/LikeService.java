package com.questapp.service;

import com.questapp.dto.request.LikeCreateRequest;
import com.questapp.model.Like;
import com.questapp.model.Post;
import com.questapp.model.User;
import com.questapp.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final UserService userService;

    public LikeService(LikeRepository likeRepository, PostService postService, UserService userService) {
        this.likeRepository = likeRepository;
        this.postService = postService;
        this.userService = userService;
    }


    public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return likeRepository.findAllByUserIdAndPostId(userId.get(), postId.get());
        }else if (userId.isPresent()){
            return likeRepository.findAllByUserId(userId.get());
        }else if (postId.isPresent()){
            return likeRepository.findAllByPostId(postId.get());
        }
        return likeRepository.findAll();
    }

    public Like getLikeById(Long id) {
       return likeRepository.findById(id).orElseThrow(() -> new RuntimeException("Like not found"));
    }

    public void createLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getUser(likeCreateRequest.getUserId());
        Post post = postService.getPostById(likeCreateRequest.getPostId());
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
    }
    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }


}
