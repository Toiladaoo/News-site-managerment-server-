package com.example.deliveryecommercebackend.model;

import com.example.deliveryecommercebackend.DTO.NewsCreateDTO;
import com.example.deliveryecommercebackend.repository.ActionRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Date;
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
    @Column(name = "sub_content")
    private String sub_content;
    @Column(name = "image")
    private String image;
//    @Column(name = "news_status")
//    private String news_status;
    @Column(name = "comment_status")
    private boolean comment_status;
    @Column(updatable = false)
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;
    @JsonIgnore
    private boolean is_delete;

    @ManyToOne
    @JoinColumn(name="user_id")
//    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name="news_type_id")
//    @JsonBackReference
    private NewsType newsType;

    @ManyToOne
    @JoinColumn(name="action_id")
//    @JsonBackReference
    private Action action;

//    @OneToMany(mappedBy = "comment_id", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Comment> comments;


    public void setDataCreate(NewsCreateDTO news, NewsType newsType, User user){
        this.setId(news.getId());
        this.setTitle(news.getTitle());
        this.setContent(news.getContent());
        this.setSub_content(news.getSub_content());
        this.setImage(news.getImage());
        this.setCreated(java.sql.Date.valueOf(LocalDate.now()));
        this.setUpdated(java.sql.Date.valueOf(LocalDate.now()));
        this.setComment_status(true);

        this.setNewsType(newsType);
        this.setUser(user);
    }
    public void setDataUpdated(NewsCreateDTO news){
        this.setId(news.getId());
        this.setTitle(news.getTitle());
        this.setContent(news.getContent());
        this.setImage(news.getImage());
        this.setUpdated(java.sql.Date.valueOf(LocalDate.now()));
    }
}