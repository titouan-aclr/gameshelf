package com.titouanaclr.gameshelf.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Location> locations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserGame> games;
}
