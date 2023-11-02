package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Action;
import com.example.deliveryecommercebackend.model.News;
import com.example.deliveryecommercebackend.model.Role;
import com.example.deliveryecommercebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, String> {
    @Query("SELECT u FROM News u WHERE u.is_delete = false")
    List<News> findNoneDeleteNews();
    @Query("SELECT u FROM News u WHERE u.is_delete = false AND u.action = :action")
    List<News> findNoneDeleteNewsByAction(@Param("action")Action action);
    @Query("SELECT u FROM News u WHERE u.is_delete = false AND u.id = :id")
    News findNewsById(@Param("id") String id);
}
