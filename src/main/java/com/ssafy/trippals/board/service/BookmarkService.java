package com.ssafy.trippals.board.service;


import com.ssafy.trippals.board.dto.BoardParamDto;
import com.ssafy.trippals.board.dto.BoardResultDto;
import com.ssafy.trippals.board.dto.BoardUserVO;

public interface BookmarkService {
    BoardResultDto bookmarkList(BoardParamDto boardParamDto);

    int bookmarkInsert(BoardUserVO boardUserVO);
    void bookmarkUpdate(BoardUserVO boardUserVO);
    void bookmarkDelete(BoardUserVO boardUserVO);

//    int countBookmark(int userSeq);
}
