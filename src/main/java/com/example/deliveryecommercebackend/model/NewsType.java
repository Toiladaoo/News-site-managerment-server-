package com.example.deliveryecommercebackend.model;

import com.example.deliveryecommercebackend.DTO.NewsTypeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="news_type")
public class NewsType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String newsType_id;
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "des")
    private String des;
    @Column(updatable = false)
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;
    @JsonIgnore
    private boolean is_delete;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    @ToString.Exclude
    private NewsType parentType;

    public void setDataCreate(NewsTypeDTO news){
        this.setCode(news.getCode());
        this.setName(news.getName());
        this.setDes(news.getDes());
        this.set_delete(false);
    }
    public void setDataUpdate(NewsTypeDTO news){
        this.setName(news.getName());
        this.setDes(news.getDes());
        this.set_delete(false);
    }
}