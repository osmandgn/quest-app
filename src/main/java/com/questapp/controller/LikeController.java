package com.questapp.controller;

import com.questapp.dto.request.LikeCreateRequest;
import com.questapp.model.Like;
import com.questapp.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @GetMapping
    public ResponseEntity<List<Like>> getAllLikes(@RequestParam Optional<Long> userId, 
                                                  @RequestParam Optional<Long> postId){
        List<Like> likes = likeService.getAllLikes(userId, postId);
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Like> getOneLike(@PathVariable Long id){
        Like like = likeService.getLikeById(id);
        return ResponseEntity.ok(like);
    }

    @PostMapping
    public ResponseEntity<String> createLike(@RequestBody LikeCreateRequest likeCreateRequest){
        likeService.createLike(likeCreateRequest);
        String message = "Like created successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLike(@PathVariable Long id){
        likeService.deleteLike(id);
        String message = "Like deleted";
        return ResponseEntity.ok(message);
    }




}
