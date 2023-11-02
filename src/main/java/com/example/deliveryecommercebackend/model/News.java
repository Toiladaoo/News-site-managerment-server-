package com.example.deliveryecommercebackend.model;

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
    @JoinColumn(name="type_id")
    @JsonBackReference
    private NewsType newsType;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<News> news;
}