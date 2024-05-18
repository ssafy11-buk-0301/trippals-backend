package com.ssafy.trippals.board.service;


import com.ssafy.trippals.board.dto.BoardDto;
import com.ssafy.trippals.board.dto.BoardResultDto;
import com.ssafy.trippals.board.dto.BoardUserVO;

import java.util.List;

public interface BookmarkService {
    List<BoardDto> bookmarkList(int userSeq);

    void bookmarkInsert(BoardUserVO boardUserVO);
    void bookmarkUpdate(BoardUserVO boardUserVO);
    void bookmarkDelete(BoardUserVO boardUserVO);
}
