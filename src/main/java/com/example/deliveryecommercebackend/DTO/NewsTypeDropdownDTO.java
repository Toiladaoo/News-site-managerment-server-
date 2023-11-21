package com.example.deliveryecommercebackend.DTO;

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
public class NewsTypeDropdownDTO {

    private String code;
    private String content;

    public NewsTypeDropdownDTO(NewsType news) {
        this.code = news.getCode();
        this.content = news.getName();
    }
    public void setData(NewsType news){
        this.setCode(news.getCode());
        this.setContent(news.getName());
    }
}
