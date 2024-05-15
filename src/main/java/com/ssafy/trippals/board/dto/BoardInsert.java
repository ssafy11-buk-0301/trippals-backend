package com.ssafy.trippals.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardInsert {
    private Integer user_seq;
    private String title;
    private String content;
    private boolean is_draft;
    private Integer route_seq;
}