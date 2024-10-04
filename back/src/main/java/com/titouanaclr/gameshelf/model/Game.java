package com.titouanaclr.gameshelf.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "min_players")
    private int minPlayers;

    @Column(name = "max_players")
    private int maxPlayers;

    @Column
    private String description;

    @Column(name = "year_published")
    private int yearPublished;

    @ManyToMany
    @JoinTable(
            name = "game_category",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "game")
    private List<UserGame> userGames;

}
