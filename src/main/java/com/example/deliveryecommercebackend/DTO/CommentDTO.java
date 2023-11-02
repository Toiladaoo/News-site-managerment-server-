package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Comment;
import com.example.deliveryecommercebackend.model.News;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private String id;
    private String content;
    private String user_id;
    private String news_id;
    private Date created;
    private Date updated;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.user_id = comment.getContent();
        this.news_id = comment.getContent();
        this.created = comment.getCreated();
        this.updated = comment.getUpdated();
    }

    public void setData(News comment){
        this.setId(comment.getId());
        this.setContent(comment.getContent());
        this.setCreated(comment.getCreated());
        this.setUpdated(comment.getUpdated());
    }
}
