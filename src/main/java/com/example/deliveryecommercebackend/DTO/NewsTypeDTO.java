package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.News;
import com.example.deliveryecommercebackend.model.NewsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsTypeDTO {

    private String id;
    private String name;
    private String des;
    private Date created;
    private Date updated;

    public NewsTypeDTO(NewsType news) {
        this.id = news.getId();
        this.name = news.getName();
        this.des = news.getDes();
        this.created = news.getCreated();
        this.updated = news.getUpdated();
    }

    public void setData(NewsType news){
        this.setId(news.getId());
        this.setName(news.getName());
        this.setDes(news.getDes());
        this.setCreated(news.getCreated());
        this.setUpdated(news.getUpdated());
    }
}
