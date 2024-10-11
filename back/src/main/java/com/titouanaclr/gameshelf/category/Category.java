package com.titouanaclr.gameshelf.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.titouanaclr.gameshelf.game.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Game> games;

}
