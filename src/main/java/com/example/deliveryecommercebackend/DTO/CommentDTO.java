package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Comment;
import com.example.deliveryecommercebackend.model.News;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private String id;
    private String content;
    private String user_id;
    private String user_name;
    private String news_name;
    private String news_id;
    private Date created;
    private Date updated;

    public CommentDTO(Comment comment) {
        this.id = comment.getComment_id();
        this.content = comment.getContent();
        this.user_id = comment.getUser().getUser_id();
        this.news_id = comment.getNews().getId();
        this.created = comment.getCreated();
        this.updated = comment.getUpdated();
    }

    public void setData(Comment comment){
        this.setId(comment.getComment_id());
        this.setUser_name(comment.getUser().getFullName());
        this.setNews_name(comment.getNews().getTitle());
        this.setContent(comment.getContent());
        this.setCreated(comment.getCreated());
        this.setUpdated(comment.getUpdated());
    }
}
