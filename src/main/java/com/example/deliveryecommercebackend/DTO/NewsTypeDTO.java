package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.News;
import com.example.deliveryecommercebackend.model.NewsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsTypeDTO {

    private String id;
    private String code;
    private String name;
    private String des;
    private Date created;
    private Date updated;

    public NewsTypeDTO(NewsType news) {
        this.id = news.getNewsType_id();
        this.code = news.getCode();
        this.name = news.getName();
        this.des = news.getDes();
        this.created = news.getCreated();
        this.updated = news.getUpdated();
    }
    public void setData(NewsType news){
        this.setId(news.getNewsType_id());
        this.setCode(news.getCode());
        this.setName(news.getName());
        this.setDes(news.getDes());
        this.setCreated(news.getCreated());
        this.setUpdated(news.getUpdated());
    }
}
