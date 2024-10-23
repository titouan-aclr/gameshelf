package com.titouanaclr.gameshelf.exchange;

import com.titouanaclr.gameshelf.game.Game;
import com.titouanaclr.gameshelf.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@Entity
@Table(name = "exchange")
@EntityListeners(AuditingEntityListener.class)
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    @Column(name = "start_date", nullable = false, updatable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

}
