package com.ssafy.trippals.board.entity;

import com.ssafy.trippals.route.entity.Route;
import com.ssafy.trippals.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    private String title;
    private String content;
    private int readCount;
    private boolean isDraft;
    @Column(name = "reg_dt")
    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne
    @JoinColumn(name = "route_seq")
    private Route route;
}
