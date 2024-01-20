package com.example.bar_be_que.repository;

import com.example.bar_be_que.model.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
public interface  BurgerRepository extends JpaRepository<Burger, Long>{
}
