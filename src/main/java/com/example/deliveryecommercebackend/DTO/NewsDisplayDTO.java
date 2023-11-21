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
public class NewsDisplayDTO {
    private String id;
    private String title;
    private String sub_content;
    private String news_type_name;
    private String user_name;
    @LastModifiedDate
    private Date updated;

    public NewsDisplayDTO(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.sub_content = news.getSub_content();
        this.updated = news.getUpdated();
        this.user_name = news.getUser().getFullName();

        this.news_type_name = news.getNewsType().getName();
    }

    public void setData(News news){
        this.setId(news.getId());
        this.setTitle(news.getTitle());
        this.setSub_content(news.getSub_content());
        this.setUpdated(news.getUpdated());
        this.setUser_name(news.getUser().getFullName());

        this.setNews_type_name(news.getNewsType().getName());
    }
}
