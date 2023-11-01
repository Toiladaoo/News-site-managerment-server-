package com.example.deliveryecommercebackend.repository;

import com.example.deliveryecommercebackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
