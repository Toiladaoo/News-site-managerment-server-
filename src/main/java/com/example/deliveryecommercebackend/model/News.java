package com.example.deliveryecommercebackend.model;

import com.example.deliveryecommercebackend.DTO.NewsCreateDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "image")
    private String image;
    @Column(name = "type")
    private String type;
    @Column(name = "news_status")
    private String news_status;
    @Column(name = "comment_status")
    private String comment_status;
    @Column(updatable = false)
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;
    @JsonIgnore
    private boolean is_delete;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name="newsType_id")
    @JsonBackReference
    private NewsType newsType;

//    @OneToMany(mappedBy = "comment_id", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Comment> comments;

    public void setData(NewsCreateDTO news, NewsType newsType, User user){
        this.setId(news.getId());
        this.setTitle(news.getTitle());
        this.setContent(news.getContent());
        this.setImage(news.getImage());
        this.setCreated(news.getCreated());
        this.setUpdated(news.getUpdated());
        this.setNews_status(news.getNews_status());
        this.setComment_status(news.getComment_status());

        this.setNewsType(newsType);
        this.setUser(user);
    }
    public void setDataUpdated(NewsCreateDTO news){
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