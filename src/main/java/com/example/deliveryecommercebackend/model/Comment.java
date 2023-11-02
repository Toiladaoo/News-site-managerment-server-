package com.example.deliveryecommercebackend.model;

import com.example.deliveryecommercebackend.utils.Auditor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
@Entity

@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String comment_id;
    @Column(name = "content")
    private String content;
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name="news_id")
    @JsonBackReference
    private News news;
}
