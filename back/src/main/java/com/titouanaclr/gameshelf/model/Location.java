package com.titouanaclr.gameshelf.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "location")
    private List<UserGame> games;

}
