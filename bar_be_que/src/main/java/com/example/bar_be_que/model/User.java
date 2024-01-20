package com.example.bar_be_que.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String username;

    @Column(length = 250)
    private String password;

    @Column(length = 20)
    private String role;

    // Orders completed by the user
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private Set<Order> orders;
}
