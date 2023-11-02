package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.News;
import com.example.deliveryecommercebackend.model.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsTypeRepository extends JpaRepository<NewsType, String> {
    @Query("SELECT u FROM NewsType u WHERE u.is_delete = false")
    List<NewsType> findNoneDeleteNewsType();
    @Query("SELECT u FROM NewsType u WHERE u.is_delete = false AND u.newsType_id = :id")
    NewsType findNewsTypeById(@Param("id") String id);
}
