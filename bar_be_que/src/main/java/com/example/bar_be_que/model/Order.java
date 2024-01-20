package com.example.bar_be_que.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String guest;

    @Column
    private Boolean completed;

    @Column
    private Date date_set;

    @ManyToOne
    @JoinColumn(name = "burger_id")
    private Burger burger;

    // Staff that completed the order
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User staff;
}