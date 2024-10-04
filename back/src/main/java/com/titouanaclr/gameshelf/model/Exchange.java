package com.titouanaclr.gameshelf.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "exchange")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "lender_id", nullable = false)
    private User lender;

    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private User borrower;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @CreatedDate
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

}
