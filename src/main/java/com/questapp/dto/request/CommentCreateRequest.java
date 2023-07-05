package com.questapp.dto.request;

import lombok.Data;

@Data
public class CommentCreateRequest {

    private Long userId;

    private Long postId;

    private String text;
}
