package com.titouanaclr.gameshelf.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "user_game")
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

    @CreatedDate
    @Column(name = "registered_date")
    private Date registeredDate;

}
