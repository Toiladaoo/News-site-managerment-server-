package com.example.deliveryecommercebackend.DTO;

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
public class NewsDTO {

    private String id;
    private String title;
    private String content;
    private String image;
    private Date created;
    private Date updated;
    private String news_status;
    private String comment_status;

    public NewsDTO(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.content = news.getContent();
        this.image = news.getImage();
        this.created = news.getCreated();
        this.updated = news.getUpdated();
        this.news_status = news.getNews_status();
        this.comment_status = news.getComment_status();
    }

    public void setData(News news){
        this.setId(news.getId());
        this.setTitle(news.getTitle());
        this.setContent(news.getContent());
        this.setImage(news.getImage());
        this.setCreated(news.getCreated());
        this.setUpdated(news.getUpdated());
        this.setNews_status(news.getNews_status());
        this.setComment_status(news.getComment_status());
    }
}
