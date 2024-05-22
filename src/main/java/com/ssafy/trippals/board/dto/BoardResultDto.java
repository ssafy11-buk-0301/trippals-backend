package com.ssafy.trippals.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardResultDto {
	private BoardDto dto;
	private List<BoardDto> list;
	private int count;//pagination
	private boolean checkBookmark;
}
