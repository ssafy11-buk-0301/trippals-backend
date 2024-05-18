package com.ssafy.trippals.board.dao;

import com.ssafy.trippals.board.dto.BoardDto;
import com.ssafy.trippals.board.dto.BoardUserVO;
import com.ssafy.trippals.board.dto.BookmarkDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookmarkDao {
    List<BoardDto> findBookmarkByUserSeq(int userSeq);
    Integer insertBookmark(BoardUserVO boardUserVO);
    Integer updateBookmark(BoardUserVO boardUserVO);
    Integer deleteBookmark(BoardUserVO boardUserVO);

}
