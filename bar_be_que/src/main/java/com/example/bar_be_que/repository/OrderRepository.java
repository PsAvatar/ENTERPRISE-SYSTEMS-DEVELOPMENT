package com.example.bar_be_que.repository;

import com.example.bar_be_que.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCompleted(Boolean state);
}