package com.ssafy.trippals.board.entity;

import com.ssafy.trippals.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BoardBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private int seq;

    @ManyToOne
    @JoinColumn(name = "board_seq")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;
}
