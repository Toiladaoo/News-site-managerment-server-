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
    private String news_type_code;
    private String news_type_name;
    private String user_id;
    private String user_name;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;
    private String news_status;
    private boolean comment_status;

    public NewsCreateDTO(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.sub_content = news.getSub_content();
        this.content = news.getContent();
        this.image = news.getImage();
        this.created = news.getCreated();
        this.updated = news.getUpdated();
        this.news_status = news.getNews_status();
        this.comment_status = true;

        this.user_id = news.getUser().getUser_id();
        this.news_type_code = news.getNewsType().getCode();
        this.news_type_name = news.getNewsType().getName();
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

        this.setUser_id(news.getUser().getUser_id());
        this.setNews_type_code(news.getNewsType().getNewsType_id());
        this.setNews_type_name(news.getNewsType().getName());
    }
}
