package com.ssafy.trippals.board.dao;

import com.ssafy.trippals.board.dto.BoardDto;
import com.ssafy.trippals.board.dto.BoardParamDto;
import com.ssafy.trippals.board.dto.BoardUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookmarkDao {
    List<BoardDto> findBookmarkByUserSeq(BoardParamDto boardParamDto);
    Integer insertBookmark(BoardUserVO boardUserVO);
    Integer countBookmark(BoardParamDto boardParamDto);
    Integer deleteBookmark(BoardUserVO boardUserVO);

    Integer checkBookmarkByUserSeq(BoardUserVO boardUserVO);
}
