package com.ssafy.trippals.board.service;

import com.ssafy.trippals.board.dto.BoardDto;
import com.ssafy.trippals.board.dto.BoardParamDto;
import com.ssafy.trippals.board.dto.BoardResultDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {
    BoardResultDto boardList(BoardParamDto boardParamDto);
    BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);

    BoardResultDto boardDetail(BoardParamDto boardParamDto);

    BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request);
    BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request);
    BoardResultDto boardDelete(int boardId,int userSeq);
}

