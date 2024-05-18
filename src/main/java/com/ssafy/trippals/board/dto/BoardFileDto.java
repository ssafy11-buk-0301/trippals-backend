package com.ssafy.trippals.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardFileDto {
    private int fileId;
    private int boardSeq;
    private String fileName;
    private long fileSize;
    private String fileContentType;
    private String fileUUID;
    private LocalDateTime regDt;
}