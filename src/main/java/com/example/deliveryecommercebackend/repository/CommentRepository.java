package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    @Query("SELECT u FROM Comment u")
    List<Comment> findNoneDeleteComment();
    @Query("SELECT u FROM Comment u WHERE u.comment_id = :id")
    Comment findCommentById(@Param("id") String id);
}
