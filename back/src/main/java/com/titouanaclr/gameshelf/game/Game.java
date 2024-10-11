package com.titouanaclr.gameshelf.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.titouanaclr.gameshelf.category.Category;
import com.titouanaclr.gameshelf.usergame.UserGame;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

//    @Lob
    @Column(length = 5000)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "min_players")
    private int minPlayers;

    @Column(name = "max_players")
    private int maxPlayers;

    @Column(name = "playing_time")
    private int playingTime;

    @Column(name = "year_published")
    private int yearPublished;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_category",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "game")
    @JsonIgnore
    private List<UserGame> userGames;

}
