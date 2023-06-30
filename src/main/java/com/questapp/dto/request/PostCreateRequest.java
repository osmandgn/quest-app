package com.questapp.dto.request;

import com.questapp.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {

    private Long id;
    private String title;
    private String text;
    private Long userId;

}
