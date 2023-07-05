package com.questapp.dto.response;

import com.questapp.model.Like;
import com.questapp.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class PostResponseDTO {

    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String text;
    List<Like> postLikes;


    public PostResponseDTO(Post post, List<Like> likes){
        this.id = post.getId();
        this.title = post.getTitle();
        this.text = post.getText();
        this.userId = post.getUser().getId();
        this.userName = post.getUser().getUsername();
        this.postLikes = likes;
    }
}
