package com.questapp.dto.response;

import com.questapp.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PostResponseDTO {

    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String text;


    public PostResponseDTO(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.text = post.getText();
        this.userId = post.getUser().getId();
        this.userName = post.getUser().getUsername();
    }
}
