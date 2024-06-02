package com.ssafy.trippals.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BoardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    private String fileName;
    private int fileSize;
    private String fileContentType;
    private String fileUuid;

    @Column(name = "reg_dt")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "board_seq")
    private Board board;
}
