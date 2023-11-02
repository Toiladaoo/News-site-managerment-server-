package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.News;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsCreateDTO {

    private String id;
    private String title;
    private String sub_content;
    private String content;
    private String image;
    private String newsType_id;
    private String user_id;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;
    private String news_status;
    private String comment_status;

    public NewsCreateDTO(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.sub_content = news.getSub_content();
        this.content = news.getContent();
        this.image = news.getImage();
        this.created = news.getCreated();
        this.updated = news.getUpdated();
        this.news_status = news.getNews_status();
        this.comment_status = news.getComment_status();

        this.user_id = news.getUser().getUser_id();
        this.newsType_id = news.getNewsType().getNewsType_id();
    }

    public void setData(News news){
        this.setId(news.getId());
        this.setTitle(news.getTitle());
        this.setSub_content(news.getSub_content());
        this.setContent(news.getContent());
        this.setImage(news.getImage());
        this.setCreated(news.getCreated());
        this.setUpdated(news.getUpdated());
        this.setNews_status(news.getNews_status());
        this.setComment_status(news.getComment_status());

        this.setUser_id(news.getUser().getUser_id());
        this.setNewsType_id(news.getNewsType().getNewsType_id());
    }
}
